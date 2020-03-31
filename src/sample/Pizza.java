/**
 *
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

package sample;

import java.util.ArrayList;

public abstract class Pizza {
    protected String style;
    protected String size;
    protected ArrayList<String> toppings;

    public Pizza(String style, String size, ArrayList<String> toppings) {
        this.style = style;
        this.size = size;
        // TODO: this is a shallow copy, maybe do a deep copy
        this.toppings = (ArrayList<String>) toppings.clone();
    }

    public Pizza(String style, String size) {
        this.style = style;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    public abstract int pizzaPrice();

    public String toString() {
        // TODO: actually return a formatted string, idk what it should look like tho
        return "";
    }
}
