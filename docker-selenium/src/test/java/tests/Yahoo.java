package tests;

import org.testng.annotations.Test;
import utils.TestBase;

public class Yahoo extends TestBase {

    @Test
    public void getTitleOfYahooHomePage() {
        remoteWebDriver.get().get("http://yahoo.com");
        System.out.println(remoteWebDriver.get().getTitle());
    }
}
