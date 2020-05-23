package tests;

import org.testng.annotations.Test;
import utils.TestBase;

public class Google extends TestBase {

    @Test
    public void getTitleOfGoogleHomePage() {
        remoteWebDriver.get().get("http://google.co.uk");
        System.out.println(remoteWebDriver.get().getTitle());

    }
}
