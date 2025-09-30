# PageFactory in Selenium WebDriver

## Table of Contents
- [Introduction](#introduction)
- [Beginner's Guide](#beginners-guide)
- [Expert's Guide](#experts-guide)
- [Best Practices](#best-practices)
- [Common Pitfalls](#common-pitfalls)

## Introduction

PageFactory is a class in Selenium WebDriver that supports the Page Object Model pattern. It helps initialize WebElements of a Page Object without explicitly using `findElement` or `findElements`.

## Beginner's Guide

### What is PageFactory?

PageFactory is a factory class that helps initialize WebElements in Page Objects using `@FindBy` annotations. It makes your test code more readable and maintainable.

### Basic Example

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;
    
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
```

### How It Works
1. `@FindBy` annotation locates elements when `PageFactory.initElements()` is called
2. `PageFactory.initElements(driver, this)` initializes all the WebElements
3. Elements are lazy-loaded (found when first used)

## Expert's Guide

### Advanced @FindBy Annotations

```java
// Multiple locator strategies
@FindBy(how = How.NAME, using = "username", priority = 1)
private WebElement username;

// Using CSS Selector with multiple conditions
@FindBy(css = "input[type='text'][name='search']")
private WebElement searchField;

// Finding multiple elements
@FindBy(className = "menu-item")
private List<WebElement> menuItems;

// Chaining find elements
@FindBy(id = "parent")
private WebElement parent;

public WebElement getChildElement() {
    return parent.findElement(By.className("child"));
}
```

### AJAX Element Handling

```java
@FindBy(id = "dynamicContent")
private WebElement dynamicContent;

public void waitForContent(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(dynamicContent));
}
```

### Custom Element Types

```java
public class CheckBox {
    private WebElement element;
    
    public CheckBox(WebElement element) {
        this.element = element;
    }
    
    public void check() {
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    public boolean isChecked() {
        return element.isSelected();
    }
}

// Usage in Page Object
public class RegistrationPage {
    @FindBy(id = "terms")
    private WebElement termsCheckboxElement;
    
    private CheckBox termsCheckbox;
    
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        termsCheckbox = new CheckBox(termsCheckboxElement);
    }
    
    public void acceptTerms() {
        termsCheckbox.check();
    }
}
```

## Best Practices

1. **Initialize in Constructor**
   ```java
   public class HomePage {
       public HomePage(WebDriver driver) {
           PageFactory.initElements(driver, this);
       }
   }
   ```

2. **Use Appropriate Locators**
   - Prefer ID, then CSS, then XPath
   - Keep selectors as simple as possible

3. **Lazy Initialization**
   - PageFactory uses lazy initialization by default
   - Elements are only found when first used

4. **Use PageFactory with Page Object Model**
   - Each page should be a separate class
   - Keep page-specific methods in their respective page classes

## Common Pitfalls

1. **StaleElementReferenceException**
   - Occurs when the page changes after element initialization
   - Solution: Re-initialize elements or use PageFactory.initElements again

2. **Performance Issues**
   - Large number of elements can slow down initialization
   - Solution: Initialize only necessary elements

3. **Element Not Found**
   - Elements are not found if the page hasn't loaded
   - Solution: Add explicit waits before accessing elements

## Further Reading
- [Selenium PageFactory Documentation](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/PageFactory.html)
- [Page Object Model Best Practices](https://www.selenium.dev/documentation/guidelines/page_object_models/)
