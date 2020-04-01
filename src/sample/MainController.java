/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button addBtn,
                   removeBtn,
                   clearBtn,
                   addOrderBtn,
                   showBtn;

    @FXML
    private ComboBox<String> styleCombo,
                             sizeCombo;

    @FXML
    private ListView<String> toppingsList,
                             selectedList;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private TextArea outputTA;

    private ArrayList<String> toppings;

    private ArrayList<Pizza> order;

    public MainController() {
        toppings = new ArrayList<>();
        order = new ArrayList<>();
        // TODO load in options for comboboxes
        // TODO load in toppingsList options
    }

    @FXML
    private void loadOrderScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsscreen.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene((Pane) loader.load()));

            DetailController controller = loader.<DetailController>getController();
            controller.init(order);

            stage.show();
        }
        catch(IOException e) {
            outputTA.setText(e.toString());
        }
    }


}
