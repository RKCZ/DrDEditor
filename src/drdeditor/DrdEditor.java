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

    private Locale currentLocale;
    private MainController controller;

    @Override
    public void start(Stage stage) throws Exception {
        currentLocale = Locale.getDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/drdeditor/Main.fxml"), ResourceBundle.getBundle("drdeditor/Bundle", currentLocale));
        Parent root = (Parent) loader.load();
        controller = (MainController) loader.getController();
        controller.setWindow(stage);
        controller.setLocale(currentLocale);
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}