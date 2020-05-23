package utils;

import static utils.ProcessUtils.execute;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public class TestBase {
    private static final String SETUP_DOCKER_SCRIPT_FILE = System.getProperty("user.dir") + "/dockerup.sh";
    private static final String SCALE_CHROME_FILE = System.getProperty("user.dir") + "/docker_scale.sh";
    private static final String TEAR_DOWN_SCRIPT_FILE = System.getProperty("user.dir") + "/dockerdown.sh";
    private static final String EXPECTED_MESSAGE = "The node is registered to the hub and ready to use";
    public static ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<>();

    @BeforeSuite
    public void setUpDockerAndScaleBrowser() {
        execute(SETUP_DOCKER_SCRIPT_FILE, EXPECTED_MESSAGE);
        execute(SCALE_CHROME_FILE, true);
    }

    @BeforeClass
    public void setUp() throws Exception {
        remoteWebDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub")
                , DesiredCapabilities.chrome()));
    }

    @AfterClass
    public void tearDownBrowser() {
        remoteWebDriver.get().close();
    }

    @AfterSuite
    public void tearDownDocker() {
        System.out.println("Tearing Down The Docker");
        execute(TEAR_DOWN_SCRIPT_FILE, true);
    }

}
