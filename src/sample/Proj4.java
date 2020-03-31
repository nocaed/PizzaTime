/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Proj4 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainscreen.fxml"));
        primaryStage.setTitle("My Pizza Store");
        primaryStage.setScene(new Scene(root, 625, 675));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
