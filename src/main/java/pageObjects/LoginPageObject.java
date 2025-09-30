package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObject extends BasePageObject {

    private @FindBy(id = "text")
    WebElement usernameField;

    private @FindBy(id = "password")
    WebElement passwordField;

    private @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    public LoginPageObject() {
        super();
    }

    public void navigateToWebDriverUniversityLoginPage(){
        navigateToPage("https://www.webdriveruniversity.com/Login-Portal/index.html");
    }

    public void setUsernameField(String username){
        sendKeys(usernameField, username);
    }

    public void setPasswordField(String password){
        sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        waitForElementAndClick(loginButton);
    }
}
