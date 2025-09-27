package pajeObjects;

import driver.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Base_PO {

    /**
     * Default constructor.
     *
     * In Java, constructors are used to initialize objects when they are created.
     * They are used to set the initial state of an object.
     *
     * The no-argument constructor is a special constructor that is automatically
     * created by the Java compiler if you don't define any constructors.
     * It is used when you create an object without passing any arguments.
     *
     * We need constructors when we want to initialize objects with specific values
     * or perform some operations when an object is created.
     * In this case, we don't need to initialize the object with any specific values,
     * so we simply provide an empty constructor.
     */
    public Base_PO() {
    }

    public WebDriverWait wait;
    public WebDriver driver;

    public WebDriver getDriver() {

        return DriverFactory.getDriver();
    }

    public String generateRandomInt(int length) {

        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomString(int length) {

        return RandomStringUtils.randomAlphabetic(length);
    }

    public void navigateToPage(String url) {

        getDriver().get(url);
    }

    // Alert handler
    public void alertHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Accepts a simple alert
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    // Gets the alert text
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    // Send keys to an element
    public void sendKeys(By by, String textToSend){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(textToSend);
    }

//    // Send keys to an element
//    public void sendKeys(WebElement element, String textToSend){
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(textToSend);
//    }

    // Clicks on an element
    public void waitForElementAndClick(By by){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    // Clicks on an element
    public void waitForElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

        // Finds an element and extracts the text
    public String findElementAndGetText(By by){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getText();
    }
}
