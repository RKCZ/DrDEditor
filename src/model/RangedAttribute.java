package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Roman Kalivoda
 */
public class RangedAttribute {

    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty power = new SimpleIntegerProperty();
    private final IntegerProperty damage = new SimpleIntegerProperty();
    private final IntegerProperty shortRange = new SimpleIntegerProperty();
    private final IntegerProperty mediumRange = new SimpleIntegerProperty();
    private final IntegerProperty longRange = new SimpleIntegerProperty();

    public int getLongRange() {
        return longRange.get();
    }

    public void setLongRange(int value) {
        longRange.set(value);
    }

    public IntegerProperty longRangeProperty() {
        return longRange;
    }

    public int getMediumRange() {
        return mediumRange.get();
    }

    public void setMediumRange(int value) {
        mediumRange.set(value);
    }

    public IntegerProperty mediumRangeProperty() {
        return mediumRange;
    }
    

    public int getShortRange() {
        return shortRange.get();
    }

    public void setShortRange(int value) {
        shortRange.set(value);
    }

    public IntegerProperty shortRangeProperty() {
        return shortRange;
    }

    public int getPower() {
        return power.get();
    }

    public void setPower(int value) {
        power.set(value);
    }

    public IntegerProperty powerProperty() {
        return power;
    }

    public int getDamage() {
        return damage.get();
    }

    public void setDamage(int value) {
        damage.set(value);
    }

    public IntegerProperty damageProperty() {
        return damage;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
}
