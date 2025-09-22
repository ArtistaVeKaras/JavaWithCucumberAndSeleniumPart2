package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        WebDriver driver = null;

        String browserType = "firefox";

        switch (browserType) {
            case "chrome":
                // Setup ChromeDriver using WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Configure Chrome options
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--start-maximized");

                // Initialize FirefoxDriver with options
                driver = new ChromeDriver(options);

                // Set implicit wait
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver = new ChromeDriver();
                break;
            case "firefox":
                // Setup FirefoxDriver using WebDriverManager
                WebDriverManager.firefoxdriver().setup();

                // Configure Chrome options
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");

                // Initialize FirefoxDriver with options
                driver = new FirefoxDriver(firefoxOptions);

                // Set implicit wait
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver = new FirefoxDriver();


//                // Setup FirefoxDriver using WebDriverManager Bruno's way unfornunately not working
//                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/geckodriver.exe");
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("Invalid browser type");
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void cleanUpDriver() {
        driver.get().quit();
        driver.remove();
    }
}
