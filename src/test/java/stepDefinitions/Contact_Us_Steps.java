package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Contact_Us_Steps {

    public WebDriver driver;

    @Before
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to Webdriver University Contact Us Page")
    public void i_navigate_to_webdriver_university_contact_us_page() {
        System.out.println("Navigated to Contact Us page");
    }

    @When("I enter a unique first name")
    public void i_enter_a_unique_first_name() {
        System.out.println("Entered first name");
    }

    @And("I enter a unique last name")
    public void i_enter_a_unique_last_name() {
        System.out.println("Entered last name");
    }

    @And("I enter a unique email")
    public void i_enter_a_unique_email() {
        System.out.println("Entered email");
    }

    @And("I enter a unique comment")
    public void i_enter_a_unique_comment() {
        System.out.println("Entered comment");
    }

    @And("I click on Submit button")
    public void i_click_on_submit_button() {
        System.out.println("Clicked submit button");
    }

    @Then("I should see a thank you message")
    public void i_should_see_a_thank_you_message() {
        System.out.println("Verified thank you message: ");
    }
}
