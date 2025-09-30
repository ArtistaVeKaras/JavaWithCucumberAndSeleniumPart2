package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePageObject;
import pageObjects.LoginPageObject;


public class Login_Steps extends BasePageObject {

    private final LoginPageObject loginPageObject;

    public Login_Steps(LoginPageObject loginPageObject) {
        this.loginPageObject = loginPageObject;
    }

    @Given("I navigate to the Log in page")
    public void i_navigate_to_the_log_in_page() {
        System.out.println("Navigate to the user Login page");
        loginPageObject.navigateToWebDriverUniversityLoginPage();
    }

    @When("I enter my username {word}")
    public void i_enter_my_username(String username) {
        System.out.println("Entering the username:");
        loginPageObject.setUsernameField(username);
    }

    @And("I enter my password {word}")
    public void i_enter_my_password(String password) {
        System.out.println("Entering the password:");
        loginPageObject.setPasswordField(password);
    }

    @And("I click the submit button")
    public void i_click_the_submit_button() {
        System.out.println("Clicked the submit button");
        loginPageObject.clickLoginButton();
    }

    @Then("I should see the following validation message {}")
    public void i_should_see_the_following_validation_message(String validationMessage) {
        System.out.println("Validation message:" + validationMessage);
        loginPageObject.validateSuccessLoginMessage(validationMessage);
    }
}

