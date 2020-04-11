/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {
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

    private ArrayList<Pizza> order;

    private final int MAX_TOPPINGS = 6,
                      MIN_TOPPINGS = 1;

    private Image deluxeImage,
                  hawaiianImage,
                  byoImage;

    public MainController() {
        // instantiate container for toppings on a Pizza and the container for Pizzas
        order = new ArrayList<>();
        // create images to load into pizzaImage later on
        deluxeImage = new Image("file:deluxe.png");
        hawaiianImage = new Image("file:hawaiian.png");
        byoImage = new Image("file:buildyourown.png");
    }

    void init() {
        // initialize image of pizza
        setPizzaImage(byoImage);
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
        // Initialize a list for selected toppings
        selectedList.setItems(FXCollections.observableArrayList());
        // set an event listener for the style combo box
        styleCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("deluxe")) {
                disableToppings(true);
                setPizzaImage(deluxeImage);
            }
            else if(newValue.equalsIgnoreCase("hawaiian")) {
                disableToppings(true);
                setPizzaImage(hawaiianImage);
            }
            else {
                disableToppings(false);
                setPizzaImage(byoImage);
            }
        });
    }

    private void disableToppings(boolean disable) {
        toppingsList.setDisable(disable);
    }

    private void setPizzaImage(Image image) {
        pizzaImage.setImage(image);
    }

    @FXML
    private void addTopping() {
        if(!toppingsList.isDisabled()) {
            ObservableList<String> toppingsToAdd = selectedList.getItems();
            int numElements = toppingsToAdd.size();

            for (String topping : toppingsList.getSelectionModel().getSelectedItems()) {
                if(!toppingsToAdd.contains(topping) && numElements+1 <= MAX_TOPPINGS) {
                    toppingsToAdd.add(topping);
                    numElements++;
                }
            }

        }
    }

    @FXML
    private void removeTopping() {
        ObservableList<String> toppingsToRemove = selectedList.getItems();
        int numElements = toppingsToRemove.size();

        for (String topping : selectedList.getSelectionModel().getSelectedItems()) {
            if(numElements-1 >= MIN_TOPPINGS) {
                toppingsToRemove.remove(topping);
                numElements--;
            }
        }
    }

    @FXML
    private void clearToppings() {
        ObservableList<String> toppingsToClear = selectedList.getItems();
        toppingsToClear.remove(0, toppingsToClear.size());
    }

    @FXML
    private void updateOrder() {
        String pizzaStyle = styleCombo.getValue();
        String size = sizeCombo.getValue();
        if(pizzaStyle.equalsIgnoreCase("deluxe"))
            order.add(new Deluxe(size));
        else if(pizzaStyle.equalsIgnoreCase("hawaiian"))
            order.add(new Hawaiian(size));
        else {
            try {
                ObservableList<String> selectedToppings = selectedList.getItems();
                ArrayList<String> toppings = new ArrayList<>(selectedToppings);
                order.add(new BuildYourOwn(size, toppings));
            }
            catch(Exception e) {
                outputTA.setText(e.toString());
            }
        }
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
