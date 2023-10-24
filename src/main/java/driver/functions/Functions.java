package driver.functions;

import driver.setup.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Functions {

    private static final WebDriverWait wait = new WebDriverWait(BaseDriver.driver(), Duration.ofSeconds(10));

    private Functions(){}

    public static void navigate(String url){
        BaseDriver.driver().get(url);
    }

    //uses the main search on the website
    public static void search(String searchTerms){
        String xpath = ".//input[@data-testid = 'searchval'][@aria-owns = 'awesomplete_list_2']";
        clickXpath(xpath);
        sendKeysXpath(xpath, searchTerms);
        hitEnterXpath(xpath);
    }

    public static void addLastItemOnPage(){
        List<WebElement> itemsOnPage = findPlural("itemAddCart");
        WebElement lastitem = itemsOnPage.get(itemsOnPage.size()-1);
        lastitem.click();
        findXpath(".//*[@class = 'notification__heading']");
    }

    public static void refreshPage(){
        BaseDriver.driver().navigate().refresh();
    }

    //data-testid versions, default

    public static void sendKeys(String datatestid, String keys){

        waitUntilCssClickable(datatestid).sendKeys(keys);
    }

    public static void hitEnter(String datatestid){
        waitUntilCssClickable(datatestid).sendKeys(Keys.ENTER);
    }

    public static void click(String datatestid){
        waitUntilCssClickable(datatestid).click();
    }

    public static WebElement find(String datatestid){
        return waitUntilCssClickable(datatestid);
    }

    public static List<WebElement> findPlural(String datatestid){
        waitUntilCssClickable(datatestid);
        return BaseDriver.driver().findElements(By.cssSelector(String.format("*[data-testid = '%s']", datatestid)));
    }

    public static void emptyCart() throws InterruptedException {
        navigate("https://www.webstaurantstore.com/cart/");
        refreshPage();
        findXpath(".//h1[contains(@class, 'page-header')][text() = 'Cart']");
        clickXpath(".//button[contains(@class, 'emptyCartButton')]");
        clickXpath(".//button[contains(text(), 'Empty')][contains(@class, 'green')]");
        refreshPage();
        Thread.sleep(500);
    }

    //More complex needs - manual xpath-ing

    public static void sendKeysXpath(String xpath, String keys){
        waitUntilXPathClickable(xpath).sendKeys(keys);
    }

    public static void hitEnterXpath(String xpath){
        waitUntilXPathClickable(xpath).sendKeys(Keys.ENTER);
    }

    public static void clickXpath(String xpath){
        waitUntilXPathClickable(xpath).click();
    }

    public static WebElement findXpath(String xpath){
        return waitUntilXPathClickable(xpath);
    }

    public static List<WebElement> findPluralXpath(String xpath){
        waitUntilXPathClickable(xpath);
        return BaseDriver.driver().findElements(By.xpath(xpath));
    }

    //helper methods

    //manual xpath searches
    private static WebElement waitUntilXPathClickable(String xpath){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    //waiting until css element is clickable, used for data-testid searches
    private static WebElement waitUntilCssClickable(String datatestid){
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(String.format("*[data-testid = '%s']", datatestid))));
    }

}