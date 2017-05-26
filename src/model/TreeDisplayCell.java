package model;

import javafx.scene.control.TreeCell;

/**
 *
 * @author Roman Kalivoda
 */
public class TreeDisplayCell extends TreeCell<ITreeNode>{
    
    @Override
    public void updateItem(ITreeNode item, boolean empty) {
        super.updateItem(item, empty);
        
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(getItem().getName());
            setGraphic(null);
        }
    }
}
