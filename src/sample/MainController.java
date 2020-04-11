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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    @FXML
    private ComboBox<String> styleCombo, // holds choices for pizza styles
                             sizeCombo;  // holds choices for sizes

    @FXML
    private ListView<String> toppingsList, // holds choices for toppings
                             selectedList; // holds selected toppings

    @FXML
    private ImageView pizzaImage; // displays image of pizza style currently selected

    @FXML
    private TextArea outputTA; // displays messages to the user

    private ArrayList<Pizza> order; // holds the current pizza order

    private final int MAX_TOPPINGS = 6, // max num of toppings
                      MIN_TOPPINGS = 1; // min num of toppings

    private Image deluxeImage,   // Image of a deluxe pizza
                  hawaiianImage, // Image of a hawaiian pizza
                  byoImage;      // Image of a build your own pizza

    /**
     * Constructor for MainController that initializes pizza order collection and images.
     * @author Thomas Brewer
     */
    public MainController() {
        // instantiate container for toppings on a Pizza and the container for Pizzas
        order = new ArrayList<>();
        // create images to load into pizzaImage later on
        deluxeImage = new Image("file:deluxe.png");
        hawaiianImage = new Image("file:hawaiian.png");
        byoImage = new Image("file:buildyourown.png");
    }

    /**
     * Initializes all components of the GUI
     * @author Thomas Brewer
     */
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
            // disable topping selection and clear topping selection if deluxe or hawaiian, then load proper images
            if(newValue.equalsIgnoreCase("deluxe")) {
                disableToppings(true);
                setPizzaImage(deluxeImage);
                clearToppings();
            }
            else if(newValue.equalsIgnoreCase("hawaiian")) {
                disableToppings(true);
                setPizzaImage(hawaiianImage);
                clearToppings();
            }
            else {
                disableToppings(false);
                setPizzaImage(byoImage);
            }
        });
    }

    /**
     * Disables toppings listview.
     * @author Thomas Brewer
     * @param disable True if list is being disabled, false if being enabled
     */
    private void disableToppings(boolean disable) {
        toppingsList.setDisable(disable);
    }

    /**
     * Sets the pizza imageview to the given Image.
     * @author Thomas Brewer
     * @param image The pizza image being displayed
     */
    private void setPizzaImage(Image image) {
        pizzaImage.setImage(image);
    }

    @FXML
    /**
     * Event handler for add toppings button being pressed.
     * @author Thomas Brewer
     */
    private void addTopping() {
        // if build your own is selected, add selected topping
        if(!toppingsList.isDisabled()) {
            // fetches selected toppings
            ObservableList<String> toppingsToAdd = selectedList.getItems();
            int numElements = toppingsToAdd.size();

            // for each topping in the selected toppings, add the topping
            for (String topping : toppingsList.getSelectionModel().getSelectedItems()) {
                // do not allow repeated elements or more than 6 toppings
                if(!toppingsToAdd.contains(topping) && numElements+1 <= MAX_TOPPINGS) {
                    toppingsToAdd.add(topping);
                    numElements++;
                }
            }

            // tell the user that the topping has been added
            outputTA.setText("Topping added!");
        }
        else {
            // tell the user that they cannot add toppings atm
            outputTA.setText("Error, cannot add topping at this time.");
        }
    }

    @FXML
    /**
     * Event handler for the click of the remove button.
     * @author Thomas Brewer
     */
    private void removeTopping() {
        // fetch current toppings
        ObservableList<String> toppingsToRemove = selectedList.getItems();
        int numElements = toppingsToRemove.size();

        // for each topping in the selected current toppings, remove the topping
        for (String topping : selectedList.getSelectionModel().getSelectedItems()) {
            // do not remove if only one topping remains
            if(numElements-1 >= MIN_TOPPINGS) {
                toppingsToRemove.remove(topping);
                numElements--;
            }
        }

        // inform the user of success
        outputTA.setText("Topping removed!");
    }

    @FXML
    /**
     * Event handler for clear button.
     * @author Thomas Brewer
     */
    private void clearToppings() {
        // fetch list of current toppings
        ObservableList<String> toppingsToClear = selectedList.getItems();
        // remove all toppings in selection
        toppingsToClear.remove(0, toppingsToClear.size());
        // inform user of success
        outputTA.setText("Toppings cleared!");
    }

    @FXML
    /**
     * Event handler for add to order button.
     * @author Thomas Brewer
     */
    private void updateOrder() {
        // fetches pizza style/size from respective combo boxes
        String pizzaStyle = styleCombo.getValue();
        String size = sizeCombo.getValue();

        // add deluxe pizza
        if(pizzaStyle.equalsIgnoreCase("deluxe")) {
            order.add(new Deluxe(size));
            outputTA.setText("Deluxe pizza added to order!");
        }
        // add hawaiian pizza
        else if(pizzaStyle.equalsIgnoreCase("hawaiian")) {
            order.add(new Hawaiian(size));
            outputTA.setText("Hawaiian pizza added to order!");
        }
        // add build your own pizza
        else {
            // try to add a byo pizza
            try {
                // fetch all selected toppings and convert them into an arraylist for the BYO constructor
                ObservableList<String> selectedToppings = selectedList.getItems();
                ArrayList<String> toppings = new ArrayList<>(selectedToppings);
                // add the pizza
                order.add(new BuildYourOwn(size, toppings));
                // inform user of success
                outputTA.setText("New pizza added to order!");
            }
            catch(Exception e) {
                // output an error message in case of any errors
                outputTA.setText(e.toString().substring(e.toString().indexOf(":")+2));
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
            // load the gui in
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsscreen.fxml"));

            // set up the window
            Stage stage = new Stage();
            stage.setScene(new Scene((Pane) loader.load()));
            stage.setTitle("Order Details");
            stage.setResizable(false);

            // Call the second scene controller and initialize the gui
            DetailController controller = loader.<DetailController>getController();
            controller.init(order);

            // show the window
            stage.show();
        }
        catch(IOException e) {
            // just in case the file is missing, we display an error message
            outputTA.setText(e.toString());
        }
    }


}
