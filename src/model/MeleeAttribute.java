package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Roman Kalivoda
 */
public class MeleeAttribute {

    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty power = new SimpleIntegerProperty();
    private final IntegerProperty damage = new SimpleIntegerProperty();
    private final IntegerProperty defenceBonus = new SimpleIntegerProperty();

    public int getDefenceBonus() {
        return defenceBonus.get();
    }

    public void setDefenceBonus(int value) {
        defenceBonus.set(value);
    }

    public IntegerProperty defenceBonusProperty() {
        return defenceBonus;
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

    public int getPower() {
        return power.get();
    }

    public void setPower(int value) {
        power.set(value);
    }

    public IntegerProperty powerProperty() {
        return power;
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
