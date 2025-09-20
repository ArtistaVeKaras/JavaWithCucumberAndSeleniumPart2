package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Login_Steps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before("@Login")
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.webdriveruniversity.com/Login-Portal/index.html");
    }

    @After("@Login")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    public void AlertHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//     Accepts a simple alert
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

//     Gets the alert text
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Given("I navigate to the Log in page")
    public void i_navigate_to_the_log_in_page() {
        System.out.println("Navigate to the user Login page");
    }

    @When("I enter my username {word}")
    public void i_enter_my_username(String username) {
        System.out.println("Entering the username");
        driver.findElement(By.id("text")).sendKeys(username);
    }

    @And("I enter my password {word}")
    public void i_enter_my_password(String password) {
        System.out.println("Entering the password");
        driver.findElement(By.id("password")).sendKeys(password);
    }
    @And("I click the submit button")
    public void i_click_the_submit_button() {
        System.out.println("Clicked the submit button");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should see the following validation message {}")
    public void i_should_see_the_following_validation_message(String validationMessage) {
        System.out.println("Validation message:" + validationMessage);
//        AlertHandler(driver);
//        String alertText = getAlertText();
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        Assert.assertEquals(alertText, validationMessage);
//        acceptAlert();
    }

//    @Then("I should see the following validation message validation failed")
//    public void i_should_see_the_following_validation_message_success(String validationMessage) {
//        System.out.println("Validation message" + validationMessage);
//        AlertHandler(driver);
//        String alertText = getAlertText();
//        Assert.assertEquals(alertText, "validation failed");
//        acceptAlert();
//    }
}
