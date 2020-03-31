/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {
    private final int SMALL = 5;
    private final int MEDIUM = SMALL + 2;
    private final int LARGE = SMALL + 4;
    private final int MAX_TOPPINGS = 6;
    private final int MIN_TOPPINGS = 1;
    private final int PRICE_PER_TOPPING = 2;

    /**
     * Constructor for BuildYourOwn that hard sets style to "Build Your Own".
     * @author Thomas Brewer
     * @param size The size of the pizza
     * @param toppings The toppings of the pizza
     */
    public BuildYourOwn(String size, ArrayList<String> toppings) throws Exception {
        super("Build Your Own", size, toppings);
        if(toppings.size() < MIN_TOPPINGS || toppings.size() > MAX_TOPPINGS)
            throw new Exception("Error, number of toppings must be between 1-6 inclusive.");
    }

    @Override
    /**
     * Calculates the price of the pizza.
     * @author Thomas Brewer
     * @return Price of the pizza
     */
    public int pizzaPrice() {
        int base = size.equalsIgnoreCase("small") ? SMALL : size.equalsIgnoreCase("medium") ? MEDIUM : LARGE;
        return base + toppings.size() * PRICE_PER_TOPPING;
    }
}
