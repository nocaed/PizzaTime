/**
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

import java.util.ArrayList;

/**
 * Superclass for various types of pizzas.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */
public abstract class Pizza {
    protected String style;
    protected String size;
    protected ArrayList<String> toppings;

    /**
     * Pizza Constructor that allows for custom toppings.
     * @author Thomas Brewer
     * @param style The type of pizza (Deluxe, Hawaiian, or BuildYourOwn)
     * @param size The size of the pizza
     * @param toppings The toppings on the pizza
     */
    public Pizza(String style, String size, ArrayList<String> toppings) {
        this.style = style;
        this.size = size;
        this.toppings = (ArrayList<String>) toppings.clone();
    }

    /**
     * Pizza Constructor with no toppings, used for subclasses with preset toppings.
     * @author Thomas Brewer
     * @param style The type of pizza (Deluxe, Hawaiian, or BuildYourOwn
     * @param size The size of the pizza
     */
    public Pizza(String style, String size) {
        this.style = style;
        this.size = size;
        toppings = new ArrayList<>();
    }

    /**
     * Calculates the price of the pizza.
     * @author Thomas Brewer
     * @return Price of the pizza
     */
    public abstract int pizzaPrice();

    @Override
    /**
     * Converts this Pizza to a formatted string showing the size, style, and all toppings.
     * @author Thomas Brewer
     * @return A String describing the size, style, and toppings of the pizza.
     */
    public String toString() {
        // describe the size and style of the pizza
        String str = "A " + size + " " + style + " pizza with ";
        // holds a string of toppings
        String strToppings = "";
        // holds the number of toppings
        int numToppings = 0;

        // appends all toppings to strToppings
        for(String topping : toppings) {
            strToppings += topping.toLowerCase() + ", ";
            numToppings++;
        }

        // formats multiple toppings
        strToppings = replaceLast(strToppings, ", ", "");
        if(numToppings > 2)
            strToppings = replaceLast(strToppings, ", ", ", and ");
        else if(numToppings > 1)
            strToppings = replaceLast(strToppings, ", ", " and ");
        // formats no toppings
        else if(numToppings <= 0)
            strToppings = "no toppings";

        // append toppings to whole string and then return it
        str += strToppings + ".";
        return str;
    }

    /**
     * Helper method for formatting the end of the toppings list.
     * @author Thomas Brewer
     * @param str The string to be formatted
     * @param oldStr The substring to be replaced
     * @param newStr The substring replacing the old substring
     * @return The string with the last occurrence of oldStr replaced by newStr
     */
    private String replaceLast(String str, String oldStr, String newStr)
    {
        // last index of oldStr
        int index = str.lastIndexOf(oldStr);
        // return the unformatted string if oldStr is not in str
        if (index == -1)
            return str;
        // splice together the substring from the start of the string to index, then the newStr, then the index after
        // the substring to the end of the string
        return str.substring(0, index) + newStr + str.substring(index + oldStr.length());
    }
}
