package sample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {

    @org.junit.jupiter.api.Test
    public void pizzaPrice() throws Exception {
        ArrayList<String> toppings = new ArrayList<>(Arrays.asList("mushroom", "pepperoni"));
        BuildYourOwn myOwnTest = new BuildYourOwn("medium", toppings);
        assertEquals(11, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("small", toppings);
        assertEquals(9, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("large", toppings);
        assertEquals(13, myOwnTest.pizzaPrice());

        toppings.add("chicken");
        toppings.add("sausage");
        toppings.add("ham");

        myOwnTest = new BuildYourOwn("large", toppings);
        assertEquals(19, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("medium", toppings);
        assertEquals(17, myOwnTest.pizzaPrice());

        myOwnTest = new BuildYourOwn("small", toppings);
        assertEquals(15, myOwnTest.pizzaPrice());

        //Should throw an exception, as outlined in the Test parameter
        ArrayList<String> empty = new ArrayList<>();
        boolean found = false;
        try {
            myOwnTest = new BuildYourOwn("medium", empty);
        } catch (Exception e) {
            found = true;
        }
        assertTrue(found);

        found = false;
        toppings.add("onion");
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