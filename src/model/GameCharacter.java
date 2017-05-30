package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Roman Kalivoda
 * @version 1.0
 */
public class GameCharacter implements ITreeNode {

    private final ObjectProperty<Race> race = new SimpleObjectProperty<>();
    private final ObjectProperty<Occupation> occupation = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty maxHP = new SimpleIntegerProperty();
    private final IntegerProperty maxMag = new SimpleIntegerProperty();
    private final ListProperty<Attribute> attributes = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final IntegerProperty armor = new SimpleIntegerProperty();
    private final ListProperty<String> spells = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<String> equipment = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<String> treasure = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<MeleeAttribute> meleeWeapons = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<RangedAttribute> rangedWeapons = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final StringProperty notes = new SimpleStringProperty();

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String value) {
        notes.set(value);
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public ObservableList<RangedAttribute> getRangedWeapons() {
        return rangedWeapons.get();
    }

    public void setRangedWeapons(ObservableList<RangedAttribute> value) {
        rangedWeapons.set(value);
    }

    public ListProperty rangedWeaponsProperty() {
        return rangedWeapons;
    }

    public ObservableList<MeleeAttribute> getMeleeWeapons() {
        return meleeWeapons.get();
    }

    public void setMeleeWeapons(ObservableList<MeleeAttribute> value) {
        meleeWeapons.set(value);
    }

    public ListProperty meleeWeaponsProperty() {
        return meleeWeapons;
    }

    public ObservableList<String> getTreasure() {
        return treasure.get();
    }

    public void setTreasure(ObservableList<String> value) {
        treasure.set(value);
    }

    public ListProperty treasureProperty() {
        return treasure;
    }

    public ObservableList<String> getEquipment() {
        return equipment.get();
    }

    public void setEquipment(ObservableList<String> value) {
        equipment.set(value);
    }

    public ListProperty EquipmentProperty() {
        return equipment;
    }
    
    public ObservableList<String> getSpells() {
        return spells.get();
    }

    public void setSpells(ObservableList<String> value) {
        spells.set(value);
    }

    public ListProperty spellsProperty() {
        return spells;
    }
   
    public int getArmor() {
        return armor.get();
    }

    public void setArmor(int value) {
        armor.set(value);
    }

    public IntegerProperty armorProperty() {
        return armor;
    }

    public Race getRace() {
        return race.get();
    }

    public void setRace(Race value) {
        race.set(value);
    }

    public ObjectProperty raceProperty() {
        return race;
    }

    public Occupation getOccupation() {
        return occupation.get();
    }

    public void setOccupation(Occupation value) {
        occupation.set(value);
    }

    public ObjectProperty occupationProperty() {
        return occupation;
    }

    @Override
    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getMaxHP() {
        return maxHP.get();
    }

    public void setMaxHP(int value) {
        maxHP.set(value);
    }

    public IntegerProperty maxHPProperty() {
        return maxHP;
    }

    public int getMaxMag() {
        return maxMag.get();
    }

    public void setMaxMag(int value) {
        maxMag.set(value);
    }

    public IntegerProperty maxMagProperty() {
        return maxMag;
    }

    public ObservableList<Attribute> getAttributes() {
        return attributes.get();
    }

    public void setAttributes(ObservableList<Attribute> value) {
        attributes.set(value);
    }

    public ListProperty attributesProperty() {
        return attributes;
    }

    private void initialize() {
        Attribute strength = new Attribute();
        Attribute resistance = new Attribute();
        Attribute dexterity = new Attribute();
        Attribute intelligence = new Attribute();
        Attribute charisma = new Attribute();
        strength.setName(java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("STRENGTH"));
        resistance.setName(java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("RESISTANCE"));
        dexterity.setName(java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("DEXTERITY"));
        intelligence.setName(java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("INTELLIGENCE"));
        charisma.setName(java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("CHARISMA"));
        getAttributes().addAll(strength, dexterity, resistance, intelligence, charisma);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("NAME:")).append(getName());
        sb.append("\nRace: ").append(getRace());
        sb.append("\nOccupation: ").append(getOccupation());
        sb.append("\nMaximal Mags: ").append(getMaxMag());
        sb.append("\nMaximal Health Points: ").append(getMaxHP());
        sb.append("\nArmor: ").append(getArmor());
        sb.append("\nAttributes<");
        attributes.forEach((next) -> {
            sb.append(next.getName()).append("(").append(next.getLevel()).append("), ");
        });
        sb.append(">");
        return sb.toString();
    }
 
    public GameCharacter() {
        initialize();        
    }
}
