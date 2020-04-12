/**
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testing for the BuildYourOwn class.
 * @author Thomas Brewer
 * @author Michael McLaughlin
 */
class BuildYourOwnTest {

    /**
     * Pizza Price.  This method tests the PizzaPrice() method from BuildYourOwn with a variety of different combinations.
     * @throws Exception For testing outside of the proper bounds
     */
    @org.junit.jupiter.api.Test
    public void pizzaPrice() throws Exception {
        //Necessary for testing
        ArrayList<String> toppings = new ArrayList<>(Collections.singletonList("mushroom"));

        //Testing lower boundary: 1 topping
        BuildYourOwn myOwnTest = new BuildYourOwn("medium", toppings);
        assertEquals(9, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("small", toppings);
        assertEquals(7, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("large", toppings);
        assertEquals(11, myOwnTest.pizzaPrice());

        //Testing intermediate value: 4 toppings
        toppings.add("chicken");
        toppings.add("sausage");
        toppings.add("ham");

        myOwnTest = new BuildYourOwn("large", toppings);
        assertEquals(17, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("medium", toppings);
        assertEquals(15, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("small", toppings);
        assertEquals(13, myOwnTest.pizzaPrice());

        //Testing upper bound: 6 toppings
        toppings.add("onion");
        toppings.add("pepperoni");

        myOwnTest = new BuildYourOwn("large", toppings);
        assertEquals(21, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("medium", toppings);
        assertEquals(19, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("small", toppings);
        assertEquals(17, myOwnTest.pizzaPrice());

        //Testing below lower bound
        //Should throw an exception
        ArrayList<String> empty = new ArrayList<>();
        boolean found = false;
        try {
            myOwnTest = new BuildYourOwn("medium", empty);
        } catch (Exception e) {
            found = true;
        }
        assertTrue(found);

        //Testing above upper bound
        found = false;
        toppings.add("pineapple");
        //Should throw exception: too many toppings
        try {
            myOwnTest = new BuildYourOwn("medium", toppings);
        } catch (Exception e) {
            found = true;
        }
        assertTrue(found);
    }
}