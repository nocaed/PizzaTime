/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
        // instantiate container for toppings on a Pizza and the container for Pizzas
        toppings = new ArrayList<>();
        order = new ArrayList<>();
    }

    void init() {
        // initialize types of pizza
        ObservableList<String> pizzaTypes = FXCollections.observableArrayList(
                "Build Your Own",
                "Deluxe",
                "Hawaiian");
        // initialize sizes of pizza
        styleCombo.setItems(pizzaTypes);
        styleCombo.getSelectionModel().select(0);
        ObservableList<String> sizes = FXCollections.observableArrayList(
                "Small",
                "Medium",
                "Large");
        sizeCombo.setItems(sizes);
        sizeCombo.getSelectionModel().select(1);
        // initialize list of toppings
        ObservableList<String> toppingChoices = FXCollections.observableArrayList(
                "Beef",
                "Cheese",
                "Chicken",
                "Green Pepper",
                "Ham",
                "Mushroom",
                "Onion",
                "Pepperoni",
                "Pineapple",
                "Sausage");
        toppingsList.setItems(toppingChoices);
    }

    private void setPizzaImage(String image) {
        pizzaImage.setImage(new Image("file:" + image + ".png"));
    }

    @FXML
    /**
     * Loads the order details window.
     * @author Thomas Brewer
     */
    private void loadOrderScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsscreen.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene((Pane) loader.load()));
            stage.setTitle("Order Details");
            stage.setResizable(false);

            DetailController controller = loader.<DetailController>getController();
            controller.init(order);

            stage.show();
        }
        catch(IOException e) {
            outputTA.setText(e.toString());
        }
    }


}
