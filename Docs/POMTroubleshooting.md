# Common Maven Project Issues and Solutions

## 1. Hooks Inheritance Issue

### Error Message

```
MainRunner>AbstractTestNGCucumberTests.setUpClass:27 » InvalidMethod You're not allowed to extend classes that define Step Definitions or hooks. 
class stepDefinitions.Contact_Us_Steps extends class stepDefinitions.Base.Hooks
```

### For Beginners

**What's happening?**
You're seeing this error because you're trying to extend a class (`Hooks`) that contains Cucumber step definitions or
hooks
from another class that also has step definitions. Cucumber doesn't allow this kind of inheritance for step definition
classes
because it can lead to unexpected behavior in your tests.

**Why is this a problem?**
Imagine you have a recipe book where instructions are scattered across multiple pages that reference each other.
It would be confusing to follow, right? Similarly, Cucumber wants step definitions to be in flat, independent classes
to keep things simple and predictable.

### For Experts

**Technical Explanation**
Cucumber's test runner creates instances of step definition classes and maps Gherkin steps to methods in these classes.
When step definition classes extend other classes with step definitions or hooks, it can lead to:

- Ambiguous step matches
- Multiple execution of hooks
- Inheritance-related issues in the test context

The error is thrown by Cucumber's validation mechanism to prevent these potential problems before test execution begins.

### Solutions

#### Option 1: Use Composition Instead of Inheritance (Recommended)

```java
public class Contact_Us_Steps {
    private final Hooks hooks;

    public Contact_Us_Steps() {
        this.hooks = new Hooks();
        this.driver = hooks.getDriver(); // Add getter in Hooks class
    }

    // Your step definitions here
}
```

#### Option 2: Move Common Code to a Utility Class

```java
public class WebDriverUtils {
    public static WebDriver setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }
}
```

#### Option 3: Use Dependency Injection

If you're using a framework like Spring or Guice, use their dependency injection mechanisms instead of inheritance.

### Prevention Tips

1. Keep step definition classes flat (no inheritance from other step definition classes)
2. Use composition to share common functionality
3. Move reusable code to utility classes
4. Consider using a dependency injection framework for more complex scenarios

## 2. Feature File Not Found

### Error Message

```
[ERROR] MainRunner>AbstractTestNGCucumberTests.setUpClass:27 » IllegalArgument path must exist: C:\path\to\feature\file.feature
```

### Solution

Use the correct path format:

```bash
mvn test -Dcucumber.features="classpath:features/Login.feature"
# or
mvn test -Dcucumber.features="src/test/resources/features/Login.feature"
```

## 3. Missing Dependencies

### Error Message

```
[ERROR] java.lang.NoClassDefFoundError: io/cucumber/core/options/CucumberOptions
```

### Solution

Ensure you have the correct dependencies in your `pom.xml`:

```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.15.0</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-testng</artifactId>
    <version>7.15.0</version>
</dependency>
```

## 4. Step Definition Not Found

### Error Message

```
[ERROR] Step undefined: Given I am on the login page
```

### Solution

1. Check that your step definition class is in a package included in the `glue` path
2. Ensure the step definition method matches the Gherkin step exactly
3. Verify the step definition class is properly annotated with `@ContextConfiguration` if needed

## 5. WebDriver Not Found

### Error Message

```
[ERROR] The path to the driver executable must be set by the webdriver.chrome.driver system property
```

### Solution

Use WebDriverManager to handle driver setup:

```java
@Before
public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
}
```

## 6. Parallel Execution Issues

### Error Message

```
[ERROR] java.util.ConcurrentModificationException
```

### Solution

1. Make sure your step definition classes are thread-safe
2. Don't share state between test scenarios
3. Use dependency injection for WebDriver instances
4. Configure proper thread count in your test runner

## 7. Hooks Not Executing

### Error Message

```
Hooks defined but not being executed
```

### Solution

1. Ensure hooks are in a package included in the `glue` path
2. Check that the hook's tag expression matches your scenario tags
3. Verify the hook's order if you have multiple hooks

## 8. TestNG Configuration Issues

### Error Message

```
[ERROR] No tests found in TestSuite
```

### Solution

1. Ensure your test class extends `AbstractTestNGCucumberTests`
2. Verify your test class is in a package that Maven can discover
3. Check that your test class is named with `*Test.java` or `*Tests.java` to be picked up by Maven Surefire Plugin

## 9. Dependency Version Conflicts

### Error Message

```
[ERROR] java.lang.NoSuchMethodError
```

### Solution

1. Use Maven's dependency tree to identify conflicts:
   ```bash
   mvn dependency:tree -Dverbose
   ```
2. Exclude conflicting dependencies
3. Use the Bill of Materials (BOM) for consistent dependency versions

## 10. Reporting Issues

### Error Message

```
[ERROR] Failed to generate report
```

### Solution

1. Check write permissions in the target directory
2. Ensure the report output directory exists
3. Verify the reporting plugin configuration in your runner class

## General Troubleshooting Tips

1. **Check the Logs**: Always look at the full stack trace, not just the error message
2. **Simplify**: Try to isolate the problem by creating a minimal test case
3. **Update Dependencies**: Make sure you're using compatible versions of Cucumber, TestNG, and other libraries
4. **Clean Build**: Sometimes a clean build can resolve mysterious issues:
   ```bash
   mvn clean install
   ```
5. **Check Java Version**: Ensure you're using a compatible Java version for your Cucumber and TestNG versions