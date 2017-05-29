package drdeditor;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.GameCharacter;
import model.Group;
import model.ITreeNode;

/**
 *
 * @author Roman Kalivoda
 */
public class FileHandler {

    private final int BUFFER_SIZE = 4096;
    private final Window window;

    public FileHandler(Window window) {
        this.window = window;
    }

    public byte[] saveCharacter(GameCharacter character) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder xmle = new XMLEncoder(baos);
        xmle.writeObject(character);
        xmle.close();
        return baos.toByteArray();
    }

    public GameCharacter readCharacter(String data) {
        InputStream is = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        XMLDecoder xmld = new XMLDecoder(is);
        GameCharacter character = (GameCharacter) xmld.readObject();
        return character;
    }

    public void saveData(TreeItem<ITreeNode> data) {
        FileOutputStream fos = null;
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("DrD file", "*.DrD"));
            fc.setInitialFileName(data.getValue().getName());
            final File saveFile = fc.showSaveDialog(window);
            fos = new FileOutputStream(saveFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.setLevel(ZipOutputStream.STORED);
            data.getChildren().stream()
                    .forEach((item) -> {
                        try {
                            zos.putNextEntry(new ZipEntry(item.getValue().getName() + "/"));
                            zos.closeEntry();
                        } catch (IOException ex) {
                            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        item.getChildren().stream()
                                .forEach((c) -> {
                                    try {
                                        zos.putNextEntry(new ZipEntry(item.getValue().getName() + "/" + c.getValue().getName()));
                                        byte[] entry = saveCharacter((GameCharacter) c.getValue());
                                        zos.write(entry, 0, entry.length);
                                        zos.closeEntry();
                                    } catch (IOException ex) {
                                        Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                    });
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File error");
            alert.setHeaderText("Character was not saved!");
            alert.show();
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("data writing operation cancelled");
        } finally {
            try {
                fos.close();
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public TreeItem<ITreeNode> readData() {
        TreeItem<ITreeNode> data = null;
        ZipInputStream zis = null;
        FileInputStream fis = null;
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("DrD file", "*.DrD"));
            final File file = fc.showOpenDialog(window);
            fis = new FileInputStream(file);
            Group root = new Group();
            root.setName(file.getName().substring(0, file.getName().lastIndexOf(".")));
            data = new TreeItem<>(root);
            zis = new ZipInputStream(fis);
            ZipEntry entry;
            TreeItem<ITreeNode> groupItem = null;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
                if (entry.isDirectory()) {
                    Group group = new Group();
                    group.setName(entry.getName().substring(0, entry.getName().length() - 1));
                    groupItem = new TreeItem<>(group);
                    data.getChildren().add(groupItem);
                } else {
                    final char[] buffer = new char[BUFFER_SIZE];
                    final InputStreamReader isr = new InputStreamReader(zis);
                    final StringBuilder sb = new StringBuilder();
                    int read;
                    while ((read = isr.read(buffer, 0, buffer.length)) >= 0) {
                        sb.append(new String(buffer, 0, read));
                    }
                    GameCharacter c = readCharacter(sb.toString());
                    TreeItem<ITreeNode> item = new TreeItem<>(c);
                    c.nameProperty().addListener(((observable, oldValue, newValue) -> {
                        TreeModificationEvent<ITreeNode> treeEvent = new TreeItem.TreeModificationEvent<>(TreeItem.valueChangedEvent(), item);
                        Event.fireEvent(item, treeEvent);
                    }));
                    groupItem.getChildren().add(item);
                }
                zis.closeEntry();
            }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File error");
            alert.setHeaderText("Data were not loaded!");
            alert.setContentText("Error occurred while reading.");
            alert.show();
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("data reading operation cancelled");
        } finally {
            try {
                fis.close();
                zis.close();
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }
}