package drdeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Roman Kalivoda
 */
public class DrdEditor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/drdeditor/FXMLDocument.fxml"));
        Parent root = (Parent) loader.load();
        FXMLDocumentController controller = (FXMLDocumentController) loader.getController();
        controller.setWindow(stage);
        Scene scene = new Scene(root);

        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.setTitle("DrD Editor");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
