package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePageObject;
import pageObjects.ContactUsPageObject;


public class Contact_Us_Steps extends BasePageObject {

    private final ContactUsPageObject contactUsPageObject;

    public Contact_Us_Steps(ContactUsPageObject contactUsSteps){
        this.contactUsPageObject = contactUsSteps;
    }

    @Given("I navigate to Webdriver University Contact Us Page")
    public void i_navigate_to_webdriver_university_contact_us_page() {
        System.out.println("Navigate to Contact Us page");
        contactUsPageObject.navigateToContactUsPage();
    }

    @When("I enter a unique first name")
    public void i_enter_a_unique_first_name() {
        System.out.println("Enter first name:");
        contactUsPageObject.setFirstName();
    }

    @And("I enter a unique last name")
    public void i_enter_a_unique_last_name() {
        System.out.println("Enter last name:");
        contactUsPageObject.setLastName();
    }

    @And("I enter a unique email")
    public void i_enter_a_unique_email() {
        System.out.println("Enter your email:");
        contactUsPageObject.setEmail("akiira_falt500@gmail.com");
    }

    @And("I enter a unique comment")
    public void i_enter_a_unique_comment() {
        contactUsPageObject.setMessage();
    }

    @When("I enter a specific first name {word}")
    public void i_enter_a_specific_first_name(String firstName) {
        System.out.println("Enter specific first name: " + firstName);
        contactUsPageObject.setSpecificFirstName(firstName);
    }

    @When("I enter a specific last name {word}")
    public void i_enter_a_specific_last_name(String lastName) {
        System.out.println("Enter specific last name: " + lastName);
        contactUsPageObject.setSpecificLastName(lastName);
    }

    @When("I enter a specific email address {word}")
    public void i_enter_a_specific_email_address(String email) {
        System.out.println("Entered email: " + email);
        contactUsPageObject.setSpecificEmail(email);
    }

    @When("I enter a specific comment {string}")
    public void i_enter_a_specific_comment(String comment) {
        System.out.println("Entered comment: " + comment);
        contactUsPageObject.setSpecificComment(comment);
    }

    @And("I click on Submit button")
    public void i_click_on_submit_button() {
        System.out.println("Clicked submit button");
        contactUsPageObject.clickSubmitButton();
    }

    @Then("I should see a thank you message")
    public void i_should_see_a_thank_you_message() {
       contactUsPageObject.validateTheThankYouMessage();
    }
}
