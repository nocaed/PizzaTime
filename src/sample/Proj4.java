/**
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Begins and runs the application.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */
public class Proj4 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainscreen.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("My Pizza Store");
        primaryStage.setResizable(false);
        MainController controller = loader.<MainController>getController();
        controller.init();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
