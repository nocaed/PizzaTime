/**
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * For handling the controller operations for the details window pane.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */
public class DetailController {
    @FXML
    private TextArea orderTA; // holds the order display

    @FXML
    private Button closeBtn; // closes the window

    @FXML
    private Label priceLbl; // holds the total price of the order

    private ArrayList<Pizza> pizzas; // holds the order

    /**
     * Initializes the order view to show all pizzas in the order.
     * @author Thomas Brewer
     * @param pizzas Container that holds all pizzas in the order
     */
    void init(ArrayList<Pizza> pizzas) {
        // store order for later clearing
        this.pizzas = pizzas;
        // initialize output to blank values
        String output = "";
        double total = 0.0;
        double currPrice;
        // this object formats all floating point values into a currency format
        DecimalFormat currencyFormatter = new DecimalFormat("$#,##0.00");
        // for each pizza in the order, display the order and add the price to the total order
        for(Pizza pizza : pizzas) {
            currPrice = pizza.pizzaPrice();
            output += pizza.toString() + " Cost: " + currencyFormatter.format(currPrice) + "\n";
            total += currPrice;
        }
        // display order
        orderTA.setText(output);
        // display net total
        priceLbl.setText(currencyFormatter.format(total));
    }

    /**
     * Closes the window
     * @author Thomas Brewer
     */
    @FXML
    private void closeWindow() {
        ((Stage)closeBtn.getScene().getWindow()).close();
    }

    /**
     * Clears the order collection and resets all displays in this stage.
     * @author Thomas Brewer
     */
    @FXML
    private void clearOrder() {
        pizzas.removeAll(pizzas);
        orderTA.setText("");
        priceLbl.setText("$0.00");
    }
}
