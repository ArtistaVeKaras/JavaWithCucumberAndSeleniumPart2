package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPageObject extends BasePageObject{

    private @FindBy(name = "first_name")
    WebElement firstNameField;

    private @FindBy(name = "last_name")
    WebElement lastNameField;

    private @FindBy(name = "email")
    WebElement emailField;

    private @FindBy(name = "message")
    WebElement messageField;

    private @FindBy(css = "input[value='SUBMIT']")
    WebElement clickSubmitButton;

    private @FindBy(xpath = "//h1")
    WebElement thankYouMessage;

    public ContactUsPageObject() {
        super();
    }

    public void navigateToContactUsPage(){
        navigateToPage("https://www.webdriveruniversity.com/Contact-Us/contactus.html");
    }

    // This does work but probably not the best way to do it
    public void fillContactForm(String firstName, String lastName, String email, String message){
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(emailField, email);
        sendKeys(messageField, message);
        clickSubmitButton();
    }

    public void setFirstName(){
        sendKeys(firstNameField, "AutoFN: " + generateRandomInt(5));
    }

    public void setLastName(){
        sendKeys(lastNameField, "AutoLN:" + generateRandomInt(5));
    }

    public void setEmail(String email){
        sendKeys(emailField, email);
    }

    public void setMessage(){
        sendKeys(messageField, "AutoComment:" + generateRandomString(10));
    }

    public void clickSubmitButton(){
        waitForElementAndClick(clickSubmitButton);
    }

    public void setSpecificFirstName(String firstName){
        sendKeys(firstNameField, firstName);
    }

    public void setSpecificLastName(String lastName){
        sendKeys(lastNameField, lastName);
    }

    public void setSpecificEmail(String email){
        sendKeys(emailField, email);
    }

    public void setMessage(String message){
        sendKeys(messageField, message);
    }

//    public void validateTheThankYouMessage(String expectedMessage){
//        findElementAndGetText(thankYouMessage);
//    }

}
