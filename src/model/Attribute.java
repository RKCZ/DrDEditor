package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Roman Kalivoda
 */
public class Attribute {

    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty level = new SimpleIntegerProperty();

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int value) {
        level.set(value);
    }

    public IntegerProperty levelProperty() {
        return level;
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
