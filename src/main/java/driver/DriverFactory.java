package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GlobalVariables;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        String browserType = getBrowserType();
        if (browserType == null) {
            throw new IllegalStateException("Browser type is not specified in config.properties or there was an error reading the configuration");
        }

        WebDriver driver;
        switch (browserType) {
            case "chrome":
                // Setup ChromeDriver using WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Configure Chrome options
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");

                // Initialize ChromeDriver with options
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                // Setup FirefoxDriver using WebDriverManager
                WebDriverManager.firefoxdriver().setup();

                // Configure Firefox options
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                if (System.getenv("CI") != null) {
                    firefoxOptions.addArguments("--headless");
                }

                // Initialize FirefoxDriver with options
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        // Common configurations
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));

        return driver;
    }

    private static String getBrowserType(){
        String browserType = null;

        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            browserType = properties.getProperty("browser").toLowerCase().trim();
        } catch (IOException e) {
            logger.error("Error reading config.properties file: {}", e.getMessage());
        } finally {
            // This is necessary to ensure that the file input stream is closed even if an exception is thrown
        }
        return browserType;
    }

    public static void cleanUpDriver() {
        driver.get().quit();
        driver.remove();
    }
}
