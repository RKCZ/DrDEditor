package drdeditor.controller;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Attribute;
import model.GameCharacter;
import model.MeleeAttribute;
import model.Occupation;
import model.Race;
import model.RangedAttribute;

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

    @FXML // fx:id="nameLB"
    private Label nameLB; // Value injected by FXMLLoader

    @FXML // fx:id="raceCB"
    private ChoiceBox<Race> raceCB; // Value injected by FXMLLoader

    @FXML // fx:id="classCB"
    private ChoiceBox<Occupation> classCB; // Value injected by FXMLLoader

    @FXML // fx:id="statsTV"
    private TableView<Attribute> statsTV; // Value injected by FXMLLoader

    @FXML // fx:id="maxMagSpnr"
    private Spinner<Integer> maxMagSpnr; // Value injected by FXMLLoader

    @FXML // fx:id="maxHPSpnr"
    private Spinner<Integer> maxHPSpnr; // Value injected by FXMLLoader

    @FXML // fx:id="ArmorSpnr"
    private Spinner<Integer> ArmorSpnr; // Value injected by FXMLLoader

    @FXML // fx:id="spellLV"
    private ListView<String> spellLV; // Value injected by FXMLLoader

    @FXML // fx:id="meleeTV"
    private TableView<MeleeAttribute> meleeTV; // Value injected by FXMLLoader

    @FXML // fx:id="rangedTV"
    private TableView<RangedAttribute> rangedTV; // Value injected by FXMLLoader

    @FXML // fx:id="notes"
    private TextArea notes; // Value injected by FXMLLoader

    @FXML // fx:id="equipmentLV"
    private ListView<String> equipmentLV; // Value injected by FXMLLoader

    @FXML // fx:id="treasureLV"
    private ListView<String> treasureLV; // Value injected by FXMLLoader

    @FXML
    private TableColumn<Attribute, String> attrTC;

    @FXML
    private TableColumn<Attribute, Integer> levelTC;

    @FXML
    private TableColumn<Attribute, Number> correctionTC;

    @FXML
    private TabPane tabs;

    @FXML
    private Button editNoteBT;

    @FXML
    private TableColumn<MeleeAttribute, Integer> meleeWeaponAttrTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> rangedWeaponAttrTC;

    @FXML
    private TableColumn<MeleeAttribute, Integer> meleePowerTC;

    @FXML
    private TableColumn<MeleeAttribute, Integer> meleeDamageTC;

    @FXML
    private TableColumn<MeleeAttribute, Integer> defBonusTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> rangedPowerTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> rangedDamageTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> rangeTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> shortTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> mediumTC;

    @FXML
    private TableColumn<RangedAttribute, Integer> longTC;

    @FXML
    private void addSpell(ActionEvent event) {
        spellLV.getItems().add(MessageFormat.format(ResourceBundle.getBundle("drdeditor/Bundle").getString("NEW SPELL"), new Object[] {spellLV.getItems().size()+1}));
    }

    @FXML
    private void addMeleeWeapon(ActionEvent event) {
        final MeleeAttribute ma = new MeleeAttribute();
        ma.setName(ResourceBundle.getBundle("drdeditor/Bundle").getString("NEW MELEE WEAPON"));
        meleeTV.getItems().add(ma);
    }

    @FXML
    private void addRangedWeapon(ActionEvent event) {
        final RangedAttribute ra = new RangedAttribute();
        ra.setName(ResourceBundle.getBundle("drdeditor/Bundle").getString("NEW RANGED WEAPON"));
        rangedTV.getItems().add(ra);
    }

    @FXML
    private void removeSpell(ActionEvent event) {
        spellLV.getItems().removeAll(spellLV.getSelectionModel().getSelectedItems());
        spellLV.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeMeleeWeapon(ActionEvent event) {
        meleeTV.getItems().removeAll(meleeTV.getSelectionModel().getSelectedItems());
        meleeTV.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeRangedWeapon(ActionEvent event) {
        rangedTV.getItems().removeAll(rangedTV.getSelectionModel().getSelectedItems());
        rangedTV.getSelectionModel().clearSelection();
    }

    @FXML
    private void editNote(ActionEvent event) {
        if (editNoteBT.getText().equals(ResourceBundle.getBundle("drdeditor/Bundle").getString("EDIT"))) {
            notes.setEditable(true);
            editNoteBT.setText(ResourceBundle.getBundle("drdeditor/Bundle").getString("SAVE"));
        } else {
            character.setNotes(notes.getText());
            notes.setEditable(false);
            editNoteBT.setText(ResourceBundle.getBundle("drdeditor/Bundle").getString("EDIT"));
        }
    }

    @FXML
    private void addEquipment(ActionEvent event) {
        equipmentLV.getItems().add(MessageFormat.format(ResourceBundle.getBundle("drdeditor/Bundle").getString("NEW EQUIPMENT"), new Object[] {equipmentLV.getItems().size()+1}));
    }

    @FXML
    private void addTreasure(ActionEvent event) {
        treasureLV.getItems().add(MessageFormat.format(ResourceBundle.getBundle("drdeditor/Bundle").getString("NEW TREASURE"), new Object[] {treasureLV.getItems().size()+1}));
    }

    @FXML
    private void removeEquipment(ActionEvent event) {
        equipmentLV.getItems().removeAll(equipmentLV.getSelectionModel().getSelectedItems());
        equipmentLV.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeTreasure(ActionEvent event) {
        treasureLV.getItems().removeAll(treasureLV.getSelectionModel().getSelectedItems());
        treasureLV.getSelectionModel().clearSelection();
    }

    private <T> void commitEditorText(Spinner<T> spinner) {
        if (!spinner.isEditable()) {
            return;
        }
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
        assert raceCB != null : "fx:id=\"raceCB\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert classCB != null : "fx:id=\"classCB\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert statsTV != null : "fx:id=\"statsTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert maxMagSpnr != null : "fx:id=\"maxMagSpnr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert maxHPSpnr != null : "fx:id=\"maxHPSpnr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert ArmorSpnr != null : "fx:id=\"ArmorSpnr\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert spellLV != null : "fx:id=\"spellLV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert meleeTV != null : "fx:id=\"meleeTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert rangedTV != null : "fx:id=\"rangedTV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert notes != null : "fx:id=\"notes\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert equipmentLV != null : "fx:id=\"equipmentLV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert treasureLV != null : "fx:id=\"treasureLV\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        initializeTabs();

        raceCB.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Race> observable, Race oldValue, Race newValue) -> {
            character.setRace(newValue);
            System.out.println("changed " + oldValue + " to " + character.getRace());
        });

        classCB.getSelectionModel().selectedItemProperty().addListener(((ObservableValue<? extends Occupation> observable, Occupation oldValue, Occupation newValue) -> {
            character.setOccupation(newValue);
            System.out.println("changed " + oldValue + " to " + character.getOccupation());
        }));

        maxMagSpnr.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                return;
            }
            commitEditorText(maxMagSpnr);
            character.setMaxMag(maxMagSpnr.getValueFactory().getValue());
            System.out.println("changed to " + character.getMaxMag());
        }));

        maxHPSpnr.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                return;
            }
            commitEditorText(maxHPSpnr);
            character.setMaxHP(maxHPSpnr.getValueFactory().getValue());
            System.out.println("changed to " + character.getMaxHP());
        }));

        ArmorSpnr.focusedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                return;
            }
            commitEditorText(ArmorSpnr);
            character.setArmor(ArmorSpnr.getValueFactory().getValue());
            System.out.println("changed to " + character.getArmor());
        }));

        spellLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        meleeTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rangedTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        equipmentLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treasureLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        spellLV.setOnEditCommit(event -> {
            if(!spellLV.getItems().contains(event.getNewValue()))
                spellLV.getItems().set(event.getIndex(), event.getNewValue());
        });
        
         equipmentLV.setOnEditCommit(event -> {
            if(!equipmentLV.getItems().contains(event.getNewValue()))
                equipmentLV.getItems().set(event.getIndex(), event.getNewValue());
        });
         
          treasureLV.setOnEditCommit(event -> {
            if(!treasureLV.getItems().contains(event.getNewValue()))
                treasureLV.getItems().set(event.getIndex(), event.getNewValue());
        });
    }

    private void initializeTabs() {
        maxMagSpnr.getValueFactory().setConverter(new IntegerStringConverter());
        maxMagSpnr.getEditor().setTextFormatter(getTextFormatter());
        maxHPSpnr.getValueFactory().setConverter(new IntegerStringConverter());
        maxHPSpnr.getEditor().setTextFormatter(getTextFormatter());
        ArmorSpnr.getValueFactory().setConverter(new IntegerStringConverter());
        ArmorSpnr.getEditor().setTextFormatter(getTextFormatter());
        meleePowerTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        meleeDamageTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        defBonusTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        rangedPowerTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        rangedDamageTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        shortTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        mediumTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        longTC.setCellFactory(TextFieldTableCell.forTableColumn(getIntegerConverter()));
        levelTC.setCellFactory(TextFieldTableCell.forTableColumn(getLevelConvertor()));
        correctionTC.setCellValueFactory(value -> new IntegerBinding() {
            {
                super.bind(value.getValue().levelProperty());
            }

            @Override
            protected int computeValue() {
                int level = value.getValue().levelProperty().getValue();
                if (level == 0) {
                    return 0;
                }
                if (level == 1) {
                    return -5;
                }
                if (level < 4) {
                    return -4;
                }
                if (level < 6) {
                    return -3;
                }
                if (level < 8) {
                    return -2;
                }
                if (level < 10) {
                    return -1;
                }
                if (level < 13) {
                    return 0;
                }
                if (level < 15) {
                    return 1;
                }
                if (level < 17) {
                    return 2;
                }
                if (level < 19) {
                    return 3;
                }
                if (level < 21) {
                    return 4;
                } else {
                    return 5;
                }
            }
        });

    }

    private StringConverter<Integer> getIntegerConverter() {
        return new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                if (object == null) {
                    return "";
                }
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                try {
                    int c = Integer.parseInt(string);
                    return c;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        };
    }

    private StringConverter<Integer> getLevelConvertor() {
        return new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                if (object == null) {
                    return "";
                }
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                try {
                    int c = Integer.parseInt(string);
                    if (c < 1) {
                        return 1;
                    } else if (c > 21) {
                        return 21;
                    } else {
                        return c;
                    }
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        };
    }

    public void updateView() {
        statsTV.setItems(character.getAttributes());
        nameLB.setText(character.getName());
        raceCB.setValue(character.getRace());
        classCB.setValue(character.getOccupation());
        maxMagSpnr.getValueFactory().setValue(character.getMaxMag());
        maxHPSpnr.getValueFactory().setValue(character.getMaxHP());
        ArmorSpnr.getValueFactory().setValue(character.getArmor());
        spellLV.setItems(character.getSpells());
        meleeTV.setItems(character.getMeleeWeapons());
        rangedTV.setItems(character.getRangedWeapons());
        notes.setText(character.getNotes());
        equipmentLV.setItems(character.getEquipment());
        treasureLV.setItems(character.getTreasure());
    }

    private TextFormatter<String> getTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = getFilter();
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        return textFormatter;
    }

    private UnaryOperator<TextFormatter.Change> getFilter() {
        return change -> {
            String text = change.getText();
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i))) {
                    return null;
                }
            }
            return change;
        };
    }
}
