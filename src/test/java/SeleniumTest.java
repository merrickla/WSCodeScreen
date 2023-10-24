import driver.functions.FunctionAsserts;
import driver.functions.Functions;
import org.junit.jupiter.api.Test;

class SeleniumTest extends StandardSetup {
    @Test
    void stainlessSteelTableTest() throws InterruptedException {

        Functions.navigate("https://www.webstaurantstore.com/");
        Functions.search("stainless work table");
        FunctionAsserts.assertAllItemDescriptions("Table");
        Functions.addLastItemOnPage();
        FunctionAsserts.assertCartHasAnItem();
        Functions.emptyCart();
        FunctionAsserts.cartIsEmpty();

    }
}