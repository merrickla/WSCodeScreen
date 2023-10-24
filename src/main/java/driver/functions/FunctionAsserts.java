package driver.functions;

import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class FunctionAsserts {

    private FunctionAsserts(){}
    public static void assertAllItemDescriptions(String text){

        int pages = Integer.parseInt(Functions.findXpath(".//a[contains(@aria-label, 'last page')]").getText());
        //iterating through any pages
        for (int i = 0; i < pages; i++){
            //iterating through items on each page
            List<WebElement> items = Functions.findPlural("itemDescription");
            for (WebElement item : items){
                String itemText = item.getText();
                assertTrue(itemText.contains(text), String.format("On page %d, '%s' does not contain the phrase '%s'", i+1, itemText, text));
            }
            if (i+1 <= pages){
                Functions.clickXpath(String.format(".//a[contains(@aria-label, 'page %s')]", i+1));
            }
        }

    }

    public static void assertCartHasAnItem(){
        WebElement cartCount = Functions.findXpath(".//span[@id = 'cartItemCountSpan']");
        assertTrue(Integer.parseInt(cartCount.getText()) > 0, "Cart is empty, or worse");
    }

    public static void cartIsEmpty(){
        WebElement cartCount = Functions.findXpath(".//span[@id = 'cartItemCountSpan']");
        assertTrue(Integer.parseInt(cartCount.getText()) == 0, "Cart still has items in it");
    }

}