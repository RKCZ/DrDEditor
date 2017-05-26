package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Roman Kalivoda
 */
public class Group implements ITreeNode{

    private final StringProperty name = new SimpleStringProperty();
    private final ListProperty<GameCharacter> group = new SimpleListProperty<>();

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

    public ObservableList getGroup() {
        return group.get();
    }

    public void setGroup(ObservableList value) {
        group.set(value);
    }

    public ListProperty groupProperty() {
        return group;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName());
        group.forEach(next -> {
            sb.append(next.getName() + "\n");
        });
        return sb.toString();
    }    
}
