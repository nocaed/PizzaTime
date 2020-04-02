/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailController {
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextArea orderTA;

    @FXML
    private Button closeBtn,
                   clearBtn;

    /**
     * Initializes the order view to show all pizzas in the order.
     * @author Thomas Brewer
     * @param pizzas Container that holds all pizzas in the order
     */
    void init(ArrayList<Pizza> pizzas) {
        String output = "";
        for(Pizza pizza : pizzas) {
            output += pizza.toString() + " Cost: $" + pizza.pizzaPrice() + "\n";
        }
        orderTA.setText(output);
    }

    @FXML
    /**
     * Closes the window
     * @author Thomas Brewer
     */
    private void closeWindow() {
        ((Stage)closeBtn.getScene().getWindow()).close();
    }
}
