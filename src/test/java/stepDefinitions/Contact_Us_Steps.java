package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pajeObjects.Base_PO;

import static java.sql.DriverManager.getDriver;


public class Contact_Us_Steps extends Base_PO {

    private final WebDriver driver = getDriver();


    @Given("I navigate to Webdriver University Contact Us Page")
    public void i_navigate_to_webdriver_university_contact_us_page() {
        System.out.println("Navigate to Contact Us page");
        navigateToPage("https://www.webdriveruniversity.com/Contact-Us/contactus.html");
    }

    @When("I enter a unique first name")
    public void i_enter_a_unique_first_name() {
        System.out.println("Entered first your name:");
        sendKeys(By.name("first_name"), "FN_" + generateRandomInt(5));
    }

    @And("I enter a unique last name")
    public void i_enter_a_unique_last_name() {
        System.out.println("Entered last your name:");
        sendKeys(By.name("last_name"), "LN_" + generateRandomInt(5));
    }

    @And("I enter a unique email")
    public void i_enter_a_unique_email() {
        System.out.println("Entered your email:");
        sendKeys(By.name("email"), "akiira_flat@gmail.com");
    }

    @And("I enter a unique comment")
    public void i_enter_a_unique_comment() {
        System.out.println("Entered comment");
        sendKeys(By.name("message"),"Message: " + generateRandomString(20));
    }

    @When("I enter a specific first name {word}")
    public void i_enter_a_specific_first_name(String firstName) {
        System.out.println("Entered first name: " + firstName);
        sendKeys(By.name("first_name"), firstName);
    }

    @When("I enter a specific last name {word}")
    public void i_enter_a_specific_last_name(String lastName) {
        System.out.println("Entered last name: " + lastName);
        sendKeys(By.name("last_name"), lastName);
    }

    @When("I enter a specific email address {word}")
    public void i_enter_a_specific_email_address(String email) {
        System.out.println("Entered email: " + email);
        sendKeys(By.name("email"), email);
    }

    @When("I enter a specific comment {string}")
    public void i_enter_a_specific_comment(String comment) {
        System.out.println("Entered comment: " + comment);
        sendKeys(By.name("message"), comment);
    }

    @And("I click on Submit button")
    public void i_click_on_submit_button() {
        System.out.println("Clicked submit button");
        clickButton(By.cssSelector("input[value='SUBMIT']"));
    }

    @Then("I should see a thank you message")
    public void i_should_see_a_thank_you_message() {
        String messageText = findElementAndGetText(By.xpath("//h1"));
        Assert.assertEquals(messageText, "Thank You for your Message!");
    }
}
