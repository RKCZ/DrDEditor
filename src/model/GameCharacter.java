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

    public ObservableList getRangedWeapons() {
        return rangedWeapons.get();
    }

    public void setRangedWeapons(ObservableList value) {
        rangedWeapons.set(value);
    }

    public ListProperty rangedWeaponsProperty() {
        return rangedWeapons;
    }

    public ObservableList getMeleeWeapons() {
        return meleeWeapons.get();
    }

    public void setMeleeWeapons(ObservableList value) {
        meleeWeapons.set(value);
    }

    public ListProperty meleeWeaponsProperty() {
        return meleeWeapons;
    }

    public ObservableList getTreasure() {
        return treasure.get();
    }

    public void setTreasure(ObservableList value) {
        treasure.set(value);
    }

    public ListProperty treasureProperty() {
        return treasure;
    }

    public ObservableList getEquipment() {
        return equipment.get();
    }

    public void setEquipment(ObservableList value) {
        equipment.set(value);
    }

    public ListProperty EquipmentProperty() {
        return equipment;
    }
    
    public ObservableList getSpells() {
        return spells.get();
    }

    public void setSpells(ObservableList value) {
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

    public ObservableList getAttributes() {
        return attributes.get();
    }

    public void setAttributes(ObservableList value) {
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
        strength.setName("strength");
        resistance.setName("resistance");
        dexterity.setName("dexterity");
        intelligence.setName("intelligence");
        charisma.setName("charisma");
        getAttributes().addAll(strength, dexterity, resistance, intelligence, charisma);
        
        /*MeleeAttribute defWeapon = new MeleeAttribute();
        defWeapon.setName("Default Melee Weapon");
        getMeleeWeapons().add(defWeapon);
        
        RangedAttribute defRangedWeapon = new RangedAttribute();
        defRangedWeapon.setName("Default Ranged Weapon");
        getRangedWeapons().add(defRangedWeapon);
        
        getSpells().add("New Spell");
        getEquipment().add("new Equipment");
        getTreasure().add("new Treasure");*/
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Name: ").append(getName());
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
