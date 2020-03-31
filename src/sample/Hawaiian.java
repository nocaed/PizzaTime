/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import java.util.ArrayList;

public class Hawaiian extends Pizza {
    private final int SMALL = 8;
    private final int MEDIUM = SMALL + 2;
    private final int LARGE = SMALL + 4;

    /**
     * Constructor for Hawaiian that hard sets style to "Hawaiian" as well as the toppings.
     * @author Thomas Brewer
     * @param size The size of the pizza
     */
    public Hawaiian(String size) {
        super("Hawaiian", size);
        toppings.add("ham");
        toppings.add("pineapple");
    }

    @Override
    /**
     * Calculates the price of the pizza.
     * @author Thomas Brewer
     * @return Price of the pizza
     */
    public int pizzaPrice() {
        return size.equalsIgnoreCase("small") ? SMALL : size.equalsIgnoreCase("medium") ? MEDIUM : LARGE;
    }
}
