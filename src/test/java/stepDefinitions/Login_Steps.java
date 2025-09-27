package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.BasePageObject;


public class Login_Steps extends BasePageObject {

    private final WebDriver driver = getDriver();

    @Given("I navigate to the Log in page")
    public void i_navigate_to_the_log_in_page() {
        System.out.println("Navigate to the user Login page");
        navigateToPage("https://www.webdriveruniversity.com/Login-Portal/index.html");
    }

    @When("I enter my username {word}")
    public void i_enter_my_username(String username) {
        System.out.println("Entering the username:");
        sendKeys(By.id("text"), username);
    }

    @And("I enter my password {word}")
    public void i_enter_my_password(String password) {
        System.out.println("Entering the password:");
        sendKeys(By.id("password"), password);
    }

    @And("I click the submit button")
    public void i_click_the_submit_button() {
        System.out.println("Clicked the submit button");
        waitForElementAndClick(By.id("login-button"));
    }

    @Then("I should see the following validation message {}")
    public void i_should_see_the_following_validation_message(String validationMessage) {
        System.out.println("Validation message:" + validationMessage);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text: " + alertText);
        Assert.assertEquals(alertText, validationMessage);
        alertHandler(driver);
        acceptAlert();
    }
}

