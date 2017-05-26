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
public class GameCharacter implements ITreeNode{

    private final ObjectProperty<Race> race = new SimpleObjectProperty<>();
    private final ObjectProperty<Occupation> occupation = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty maxHP = new SimpleIntegerProperty();
    private final IntegerProperty maxMag = new SimpleIntegerProperty();
    private final ListProperty<Attribute> attributes = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final IntegerProperty armor = new SimpleIntegerProperty();

    

    public GameCharacter() {
        initializeAttributes();
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

    private void initializeAttributes() {
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
            sb.append(next.getName()).append("(").append(next.getLevel()).append(", ").append(next.getCorrection()).append("), ");
        });
        sb.append(">");
        return sb.toString();
    }

}
