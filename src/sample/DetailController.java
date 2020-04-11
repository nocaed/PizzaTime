/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class DetailController {
    @FXML
    private TextArea orderTA;

    @FXML
    private Button closeBtn,
                   clearBtn;

    @FXML
    private Label priceLbl;

    /**
     * Initializes the order view to show all pizzas in the order.
     * @author Thomas Brewer
     * @param pizzas Container that holds all pizzas in the order
     */
    void init(ArrayList<Pizza> pizzas) {
        String output = "";
        double total = 0.0;
        double currPrice;
        for(Pizza pizza : pizzas) {
            currPrice = pizza.pizzaPrice();
            output += pizza.toString() + " Cost: $" + currPrice + "\n";
            total += currPrice;
        }
        orderTA.setText(output);
        priceLbl.setText(new DecimalFormat("$#,##0.00").format(total));
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
