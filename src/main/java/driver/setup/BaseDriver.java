package driver.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseDriver{

    private static WebDriver driver;

    private BaseDriver(){}

    //Opens a chrome driver manager for the tests
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }

    //Access to the driver
    public static WebDriver driver(){
        return driver;
    }

    //Closes the web driver session
    public static void quit(){
        driver.quit();
    }

}