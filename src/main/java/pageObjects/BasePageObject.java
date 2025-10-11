package pageObjects;

import driver.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.GlobalVariables;

import java.time.Duration;

public class BasePageObject {

    public WebDriverWait wait;
    public WebDriver driver;

    public BasePageObject() {
        PageFactory.initElements(getDriver(), this);
    }

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
    }

    // Accepts a simple alert
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    // Waits for an alert and validates its text
    public void waitForAlertAndValidateText(String expectedText){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alertText, expectedText);
    }

    // Gets the alert text
    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    // Send keys to an element
    // Usa case: When you want to locate and interact with an element in one step
    public void sendKeys(By by, String textToSend) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(textToSend);
    }

    // Send keys to an element
    // Usa case: When you already have a WebElement reference (common in Page Factory)
    public void sendKeys(WebElement element, String textToSend) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(textToSend);
    }

    // Clicks on an element
    // Usa case: When you want to locate and interact with an element in one step
    public void waitForElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    // Clicks on an element
    // Usa case: When you already have a WebElement reference (common in Page Factory)
    public void waitForElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Clicks on an element
    // Usa case: When you want to locate and interact with an element in one step
    public void waitFor(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    // Clicks on an element
    // Usa case: When you already have a WebElement reference (common in Page Factory)
    public void waitForElementAndExtractText(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Finds an element and extracts the text
    // Usa case: When you want to locate and interact with an element in one step
    public String findElementAndGetText(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalVariables.DEFAULT_EXPLICIT_TIMEOUT));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getText();
    }
}
