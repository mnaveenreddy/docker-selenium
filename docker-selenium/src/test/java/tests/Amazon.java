package tests;

import org.testng.annotations.Test;
import utils.TestBase;


public class Amazon extends TestBase {

    @Test
    public void getTitleOfAmazonHomePage() {
        remoteWebDriver.get().get("https://www.amazon.in");
        System.out.println("final value: [" + remoteWebDriver.get().getTitle() + "]");
    }
}
