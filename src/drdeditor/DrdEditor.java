package drdeditor;

import drdeditor.controller.MainController;
import java.util.Locale;
import java.util.ResourceBundle;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/drdeditor/Main.fxml"), ResourceBundle.getBundle("drdeditor/Bundle", Locale.getDefault()));
        Parent root = (Parent) loader.load();
        controller = (MainController) loader.getController();
        controller.setWindow(stage);
        Scene scene = new Scene(root);

        stage.setMinHeight(600);
        stage.setMinWidth(1000);
        stage.setTitle("DrD Editor");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> { 
            e.consume();
            controller.exit(null);
                });
        stage.show();
    }
    private MainController controller;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}