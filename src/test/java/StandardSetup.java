import driver.setup.BaseDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class StandardSetup {

    @BeforeEach
    public void setup(){ BaseDriver.setup(); }


    @AfterEach
    public void quit(){ BaseDriver.quit(); }

}