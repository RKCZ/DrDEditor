package drdeditor;

/**
 * Sample Skeleton for 'FXMLDocument.fxml' Controller Class
 */
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import model.GameCharacter;
import model.Group;
import model.ITreeNode;

public class FXMLDocumentController {

    private Window window;
    private TabsController ctrl;
    private TabPane tabs;
    private final FileHandler fh = new FileHandler(window);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="newCharacterMI"
    private MenuItem newCharacterMI; // Value injected by FXMLLoader

    @FXML //fx:id="newGroupMI"
    private MenuItem newGroupMI;

    @FXML // fx:id="closeMI"
    private MenuItem closeMI; // Value injected by FXMLLoader

    @FXML // fx:id="guideMI"
    private MenuItem guideMI; // Value injected by FXMLLoader

    @FXML // fx:id="aboutMI"
    private MenuItem aboutMI; // Value injected by FXMLLoader

    @FXML // fx:id="charInspectorTV"
    TreeView<ITreeNode> charInspectorTV; // Value injected by FXMLLoader

    @FXML
    BorderPane pane;

    @FXML
    void deleteSelection(ActionEvent event) {
        TreeItem<ITreeNode> selected = charInspectorTV.getSelectionModel()
                .getSelectedItem();
        if (selected != null) {
            if (selected.getParent() == charInspectorTV.getRoot()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deleting");
                alert.setHeaderText("Do you really want to delete " + selected.getValue().getName() + " and all heroes within?");
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {
                            charInspectorTV.getRoot().getChildren().remove(selected);
                        });
            } else {
                TreeItem<ITreeNode> group = selected.getParent();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deleting");
                alert.setHeaderText("Do you really want to delete " + selected.getValue().getName() + "?");
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {
                            group.getChildren().remove(selected);
                        });
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Nothing selected!");
            alert.setContentText("Please select which hero you want to kill first");
            alert.showAndWait();
        }
    }

    @FXML
    void renameSelection(ActionEvent event) {
        TreeItem<ITreeNode> selected = charInspectorTV.getSelectionModel()
                .getSelectedItem();
        if (selected != null) {
            Optional<String> name = Optional.empty();
            boolean collision = false;
            do {
                TextInputDialog tid = new TextInputDialog("new Name");
                tid.setTitle("Choose new name");
                tid.setContentText(collision
                        ? ("This name is already taken, please choose another name")
                        : ("Please choose new name"));
                name = tid.showAndWait();
                collision = true;
            } while (nameCollides(selected.getParent().getChildren(), name));
            selected.getValue().setName(name.get());
            ctrl.updateView();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Nothing selected!");
            alert.setContentText("Please select which hero or group you want to rename first");
            alert.showAndWait();
        }
    }

    @FXML
    void addCharacter(ActionEvent event) {
        TreeItem<ITreeNode> selectedGroup = charInspectorTV.getSelectionModel()
                .getSelectedItem();
        if (selectedGroup != null) {
            if (selectedGroup.getParent() != charInspectorTV.getRoot()) {
                selectedGroup = selectedGroup.getParent(); //pridani novveho uzlu pod skupinu
            }
            selectedGroup.setExpanded(true);
            GameCharacter newCharacter = new GameCharacter();
            Optional<String> name = Optional.empty();
            boolean collision = false;
            do {
                TextInputDialog tid = new TextInputDialog("new Character");
                tid.setTitle("Choose character name");
                tid.setContentText(collision
                        ? ("This name is already taken, please choose another name")
                        : ("Please choose name of the character"));
                name = tid.showAndWait();
                collision = true;   //pri dalsim pruchodu cyklem se zmeni text v dialogu
            } while (nameCollides(selectedGroup.getChildren(), name));
            newCharacter.setName(name.get());
            TreeItem<ITreeNode> item = new TreeItem<>(newCharacter);
            newCharacter.nameProperty().addListener(((observable, oldValue, newValue) -> {
                TreeModificationEvent<ITreeNode> treeEvent = new TreeItem.TreeModificationEvent<>(TreeItem.valueChangedEvent(), item);
                Event.fireEvent(item, treeEvent);
            }));
            selectedGroup.getChildren().add(item);
            charInspectorTV.getSelectionModel().select(item);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setHeaderText("No Group selected!");
            alert.setContentText("Please select group to which new Character can be added");
            alert.showAndWait();
        }
    }

    @FXML
    void addGroup(ActionEvent event) {
        Group newGroup = new Group();
        Optional<String> name = Optional.empty();
        boolean collision = false;
        do {
            TextInputDialog tid = new TextInputDialog("new Group");
            tid.setTitle("Choose group name");
            tid.setContentText(collision
                    ? ("This name is already taken, please choose another name")
                    : ("Please choose name of the group"));
            name = tid.showAndWait();
            collision = true;
        } while (nameCollides(charInspectorTV.getRoot().getChildren(), name));
        newGroup.setName(name.get());
        TreeItem<ITreeNode> node = new TreeItem<>(newGroup);
        charInspectorTV.getRoot().getChildren().add(node);
        charInspectorTV.getSelectionModel().select(node);
    }

    private boolean nameCollides(ObservableList<TreeItem<ITreeNode>> children, Optional<String> newName) {
        return children.stream()
                .map(item -> (item.getValue()).getName())
                .anyMatch(value -> value.equalsIgnoreCase(newName.orElse("")));
    }

    @FXML
    void save(ActionEvent event) {
        fh.saveData(charInspectorTV.getRoot());
    }

    @FXML
    void load(ActionEvent event) {
        if (!charInspectorTV.getRoot().getChildren().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Possible data loss");
            alert.setHeaderText("Current state might contain unsaved data");
            alert.setContentText("Do you want to save your data now?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                save(event);
            } else if (result.get() == ButtonType.NO) {
                loadData();
            }
        } else {
            loadData();
        }
    }

    private void loadData() {
        TreeItem<ITreeNode> root = fh.readData();
        if (root != null) {
            charInspectorTV.setRoot(root);
            charInspectorTV.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void exit(ActionEvent event) {
        if (!charInspectorTV.getRoot().getChildren().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Possible data loss");
            alert.setHeaderText("Current state might contain unsaved data");
            alert.setContentText("Do you want to save your data now?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                save(event);
            } else if (result.get() == ButtonType.NO) {
                Platform.exit();
            }
        } else {
            Platform.exit();
        }
    }

    @FXML
    void showAbout(ActionEvent event) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setContentText("");
        about.setTitle("About DrD Editor");
        about.setHeaderText("");
        about.showAndWait();
    }

    @FXML
    void showGuide(ActionEvent event) {
        // TODO 
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert newCharacterMI != null : "fx:id=\"newMI\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert closeMI != null : "fx:id=\"closeMI\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert guideMI != null : "fx:id=\"guideMI\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert aboutMI != null : "fx:id=\"aboutMI\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert charInspectorTV != null : "fx:id=\"charInspectorTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        Group treeRoot = new Group();
        treeRoot.setName("DrDEditor Data");
        charInspectorTV.setRoot(new TreeItem<>(treeRoot));
        charInspectorTV.getRoot().setExpanded(true);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tabs.fxml"));
            tabs = (TabPane) loader.load();
            ctrl = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        charInspectorTV.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends TreeItem<ITreeNode>> change) -> {
            updateCenter();
        });
        updateCenter();
    }

    private void updateCenter() {
        TreeItem<ITreeNode> selected = charInspectorTV.getSelectionModel().getSelectedItem();
        if (selected == null) {
            pane.setCenter(new Label("Nothing here.\nPlease select group from list on left side\nor create your new group of characters from FILE-New Group."));
        } else {
            if (selected.getParent() == charInspectorTV.getRoot()) {
                pane.setCenter(new Label("Nothing here.\nPlease select character from list on left side\nor create new one from FILE-New Character."));
            } else {
                pane.setCenter(tabs);
                ctrl.setCharacter((GameCharacter) selected.getValue());
                ctrl.updateView();
            }
            System.out.println(selected.getValue().toString());
        }
    }

    public void setWindow(Window window) {
        this.window = window;
    }
}
