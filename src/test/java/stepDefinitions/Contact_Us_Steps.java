package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class Contact_Us_Steps {

    private WebDriver driver;

    @Before("@contactUs")
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
        driver.get("http://webdriveruniversity.com/Contact-Us/contactus.html");
    }

    @After("@contactUs")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String generateRandomInt(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    @Given("I navigate to Webdriver University Contact Us Page")
    public void i_navigate_to_webdriver_university_contact_us_page() {
        System.out.println("Navigated to Contact Us page");
    }

    @When("I enter a unique first name")
    public void i_enter_a_unique_first_name() {
        System.out.println("Entered first name");
        driver.findElement(By.name("first_name")).sendKeys("FN" + generateRandomInt(5));
    }

    @And("I enter a unique last name")
    public void i_enter_a_unique_last_name() {
        System.out.println("Entered last name");
        driver.findElement(By.name("last_name")).sendKeys("LN" + generateRandomInt(5));
    }

    @And("I enter a unique email")
    public void i_enter_a_unique_email() {
        System.out.println("Entered email");
        driver.findElement(By.name("email")).sendKeys("akiira_flat@gmail.com");
    }

    @And("I enter a unique comment")
    public void i_enter_a_unique_comment() {
        System.out.println("Entered comment");
        driver.findElement(By.name("message")).sendKeys(generateRandomString(20));
    }

    @When("I enter a specific first name {word}")
    public void i_enter_a_specific_first_name(String firstName) {
        driver.findElement(By.name("first_name")).sendKeys(firstName);
        System.out.println("Entered first name: " + firstName);
    }
    @When("I enter a specific last name {word}")
    public void i_enter_a_specific_last_name(String lastName) {
        driver.findElement(By.name("last_name")).sendKeys(lastName);
        System.out.println("Entered last name: " + lastName);
    }
    @When("I enter a specific email address {word}")
    public void i_enter_a_specific_email_address(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
        System.out.println("Entered email: " + email);
    }
    @When("I enter a specific comment {string}")
    public void i_enter_a_specific_comment(String comment) {
        driver.findElement(By.name("message")).sendKeys(comment);
        System.out.println("Entered comment: " + comment);
    }

    @And("I click on Submit button")
    public void i_click_on_submit_button() {
        System.out.println("Clicked submit button");
        driver.findElement(By.cssSelector("input[value='SUBMIT']")).click();
    }

    @Then("I should see a thank you message")
    public void i_should_see_a_thank_you_message() {
        System.out.println("Verified thank you message: ");
        WebElement thankYouMsg = driver.findElement(By.xpath("//h1"));
        Assert.assertTrue(thankYouMsg.isDisplayed());
        Assert.assertEquals(thankYouMsg.getText(), "Thank You for your Message!");
    }
}
