/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import java.util.ArrayList;

public class Deluxe extends Pizza {
    private final int SMALL = 14;
    private final int MEDIUM = SMALL + 2;
    private final int LARGE = SMALL + 4;

    /**
     * Constructor for Deluxe that hard sets style to "Deluxe" as well as the toppings.
     * @param size The size of the pizza
     */
    public Deluxe(String size) {
        super("Deluxe", size);
        toppings.add("sausage");
        toppings.add("pepperoni");
        toppings.add("green pepper");
        toppings.add("onion");
        toppings.add("mushroom");
    }

    @Override
    /**
     * Calculates the price of the pizza.
     * @return Price of the pizza
     */
    public int pizzaPrice() {
        return size.equalsIgnoreCase("small") ? SMALL : size.equalsIgnoreCase("medium") ? MEDIUM : LARGE;
    }

}
