package pageObjects;

public class LoginPageObject extends BasePageObject {

    public LoginPageObject() {
        super();
    }

    public void navigateToWebDriverUniversityLoginPage(){
        navigateToPage("https://www.webdriveruniversity.com/Login-Portal/index.html");
    }
}
