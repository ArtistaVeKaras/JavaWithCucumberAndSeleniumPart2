package stepDefinitions.Base;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.messages.types.Timestamp;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static driver.DriverFactory.cleanUpDriver;
import static driver.DriverFactory.getDriver;

public class Hooks {

    @Before
    public void setUp() {
        getDriver();
    }

    @AfterStep
    public void captureScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            byte[] screenshot = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot_" + timestamp + ".png");
        }
    }

    @After
    public void tearDown() {
        cleanUpDriver();
    }
}
