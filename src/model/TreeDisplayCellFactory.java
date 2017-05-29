package model;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 *
 * @author Roman Kalivoda
 */
public class TreeDisplayCellFactory implements Callback<TreeView<ITreeNode>, TreeDisplayCell> {

    @Override
    public TreeDisplayCell call(TreeView<ITreeNode> param) {
        return new TreeDisplayCell();
    }

}
