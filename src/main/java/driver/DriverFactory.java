package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        WebDriver driver = null;


        switch (getBrowserType()) {
            case "chrome":
                // Setup ChromeDriver using WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Configure Chrome options
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");

                // CI configuration
                String uniqueDir = Files.createTempDirectory("chrome-profile-").toString();
                chromeOptions.addArguments("--user-data-dir=" + uniqueDir);

                // Initialize ChromeDriver with options
                driver = new ChromeDriver(chromeOptions);

                // Set implicit wait
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

                // Set implicit wait
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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

    private static String getBrowserType() {
        String browserType = null;

        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
            properties.load(fileInputStream);
            browserType = properties.getProperty("browser").toLowerCase().trim();
        } catch (IOException e) {
            System.out.println("Error reading config.properties file" + e.getMessage());
        }
        return browserType;
    }

    public static void cleanUpDriver() {
        driver.get().quit();
        driver.remove();
    }
}
