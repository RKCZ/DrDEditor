package drdeditor;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
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
    
    private final Window window;
    
    public FileHandler(Window window) {
        this.window = window;
    }
    
    public void saveCharacter(GameCharacter character) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML file", "*.xml"));
        fc.setInitialFileName(character.getName());
        try {
            FileOutputStream fos = new FileOutputStream(fc.showSaveDialog(window));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder xmle = new XMLEncoder(bos);
            xmle.writeObject(character);
            xmle.close();
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File error");
            alert.setHeaderText("Character was not saved!");
            alert.setContentText("Error occurred while saving this project " + character.getName() + ". Please try again.");
            alert.show();
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public GameCharacter readCharacter() {
        FileInputStream fis = null;
        GameCharacter character = null;
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML file", "*.xml"));
            fis = new FileInputStream(fc.showOpenDialog(window));
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmld = new XMLDecoder(bis);
            character = (GameCharacter) xmld.readObject();
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File error");
            alert.setHeaderText("Character was not loaded!");
            alert.setContentText("Error occurred while reading.");
            alert.show();
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return character; 
    }
    
    public void saveData(Group data) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML file", "*.xml"));
        fc.setInitialFileName(data.getName());
        try {
            FileOutputStream fos = new FileOutputStream(fc.showSaveDialog(window));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder xmle = new XMLEncoder(bos);
            xmle.writeObject(data);
            xmle.close();
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File error");
            alert.setHeaderText("Character was not saved!");
            alert.setContentText("Error occurred while saving this project " + data.getName() + ". Please try again.");
            alert.show();
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Group readData() {
        FileInputStream fis = null;
        Group data = null;
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML file", "*.xml"));
            fis = new FileInputStream(fc.showOpenDialog(window));
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmld = new XMLDecoder(bis);
            data = (Group)xmld.readObject();
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File error");
            alert.setHeaderText("Character was not loaded!");
            alert.setContentText("Error occurred while reading.");
            alert.show();
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return data; 
    }
}
