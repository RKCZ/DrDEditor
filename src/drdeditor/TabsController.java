package drdeditor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Attribute;
import model.GameCharacter;
import model.ITreeNode;
import model.Occupation;
import model.Race;

/**
 *
 * @author Roman Kalivoda
 */
public class TabsController {
    
    private GameCharacter character;

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
     @FXML // fx:id="nameTF"
    private TextField nameTF; // Value injected by FXMLLoader

    @FXML // fx:id="raceCB"
    private ChoiceBox<Race> raceCB; // Value injected by FXMLLoader

    @FXML // fx:id="classCB"
    private ChoiceBox<Occupation> classCB; // Value injected by FXMLLoader

    @FXML // fx:id="statsTV"
    private TableView<?> statsTV; // Value injected by FXMLLoader

    @FXML // fx:id="maxMagSpnr"
    private Spinner<Integer> maxMagSpnr; // Value injected by FXMLLoader

    @FXML // fx:id="maxHPSpnr"
    private Spinner<Integer> maxHPSpnr; // Value injected by FXMLLoader

    @FXML // fx:id="ArmorSpnr"
    private Spinner<Integer> ArmorSpnr; // Value injected by FXMLLoader

    @FXML // fx:id="spellLV"
    private ListView<String> spellLV; // Value injected by FXMLLoader

    @FXML // fx:id="meleeTV"
    private TableView<?> meleeTV; // Value injected by FXMLLoader

    @FXML // fx:id="rangedTV"
    private TableView<?> rangedTV; // Value injected by FXMLLoader

    @FXML // fx:id="notesTA"
    private TextArea notesTA; // Value injected by FXMLLoader

    @FXML // fx:id="equipmentLV"
    private ListView<?> equipmentLV; // Value injected by FXMLLoader

    @FXML // fx:id="treasureLV"
    private ListView<?> treasureLV; // Value injected by FXMLLoader
    
    @FXML 
    private TableColumn<Attribute, String> attrTC;
    
    @FXML
    private TableColumn<Attribute, Integer> levelTC;
    
    @FXML
    private TableColumn<Attribute, Integer> correctionTC;
    
    @FXML
    private TabPane tabs;
    
    private void changeName() {
        if(nameTF.getText().length() != 0) {
            character.setName(nameTF.getText());
        }
        nameTF.setText(character.getName());
    }
    
    private <T> void commitEditorText(Spinner<T> spinner) {
    if (!spinner.isEditable()) return;
    String text = spinner.getEditor().getText();
    SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
    if (valueFactory != null) {
        StringConverter<T> converter = valueFactory.getConverter();
        if (converter != null) {
            T value = converter.fromString(text);
            valueFactory.setValue(value);
        }
    }
}
    
    @FXML
    void initialize() {
        assert nameTF != null : "fx:id=\"nameTF\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert raceCB != null : "fx:id=\"raceCB\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert classCB != null : "fx:id=\"classCB\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert statsTV != null : "fx:id=\"statsTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert maxMagSpnr != null : "fx:id=\"maxMagSpnr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert maxHPSpnr != null : "fx:id=\"maxHPSpnr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert ArmorSpnr != null : "fx:id=\"ArmorSpnr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert spellLV != null : "fx:id=\"spellLV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert meleeTV != null : "fx:id=\"meleeTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert rangedTV != null : "fx:id=\"rangedTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert notesTA != null : "fx:id=\"notesTA\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert equipmentLV != null : "fx:id=\"equipmentLV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert treasureLV != null : "fx:id=\"treasureLV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        
        initializeTabs();
        
        nameTF.focusedProperty().addListener((observable, oldValue, newValue) -> {
            changeName();
            System.out.println("changed " + oldValue + " to " + character.getName());
        });
               
        raceCB.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Race> observable, Race oldValue, Race newValue) -> {
            character.setRace(newValue);
            System.out.println("changed " + oldValue + " to " + character.getRace());
        });
        
        classCB.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Occupation> observable, Occupation oldValue, Occupation newValue) -> {
            character.setOccupation(newValue);
            System.out.println("changed " + oldValue + " to " + character.getOccupation());
        }));
        
        maxMagSpnr.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue) return;
            commitEditorText(maxMagSpnr);   
            character.setMaxMag(maxMagSpnr.getValueFactory().getValue());
            System.out.println("changed to " + character.getMaxMag());
        }));
        
        maxHPSpnr.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue) return;
            commitEditorText(maxHPSpnr); 
            character.setMaxHP(maxHPSpnr.getValueFactory().getValue());
            System.out.println("changed to " + character.getMaxHP());
        }));
        
        ArmorSpnr.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue) return;
            commitEditorText(ArmorSpnr);
            character.setArmor(ArmorSpnr.getValueFactory().getValue());
            System.out.println("changed to " + character.getArmor());
        }));        
    }
   //prepsat do fxml 
    private void initializeTabs() {
        //raceCB.setItems(FXCollections.observableArrayList(Race.HUMAN, Race.ELF, Race.BARBAR, Race.DWARF, Race.HOBBIT, Race.KROLL, Race.KUDUK));
       // classCB.setItems(FXCollections.observableArrayList(Occupation.ALCHEMIST, Occupation.RANGER, Occupation.SORCERER, Occupation.THIEF, Occupation.WARRIOR));
        maxMagSpnr.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0, 1));    
        maxMagSpnr.getValueFactory().setConverter(new IntegerStringConverter());
        maxHPSpnr.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0, 1));
        maxHPSpnr.getValueFactory().setConverter(new IntegerStringConverter());        
        ArmorSpnr.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 12, 0, 1));
        ArmorSpnr.getValueFactory().setConverter(new IntegerStringConverter());
        
        attrTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        levelTC.setCellValueFactory(new PropertyValueFactory<>("level"));
        levelTC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        correctionTC.setCellValueFactory(new PropertyValueFactory<>("correction"));
        correctionTC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));    
    }
    
    public void updateView() {
            statsTV.setItems(character.getAttributes());
            nameTF.setText(character.getName());
            raceCB.setValue(character.getRace());
            classCB.setValue(character.getOccupation());
            maxMagSpnr.getValueFactory().setValue(character.getMaxMag());
            maxHPSpnr.getValueFactory().setValue(character.getMaxHP());
            ArmorSpnr.getValueFactory().setValue(character.getArmor());
    }
}
