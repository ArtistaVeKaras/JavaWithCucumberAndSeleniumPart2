# DriverFactory Class Guide

## Overview

`DriverFactory` is a utility class designed to manage WebDriver instances in a Selenium WebDriver test automation
framework.
It implements the Singleton and Factory design patterns to provide a centralized way to create, manage, and dispose of
WebDriver instances.

## Purpose

1. **Centralized WebDriver Management**: Provides a single point of control for WebDriver instance creation and
   management.
2. **Thread Safety**: Uses `ThreadLocal` to ensure thread-safe WebDriver instances in parallel test execution.
3. **Browser Configuration**: Simplifies browser configuration and setup with predefined options.
4. **Resource Management**: Ensures proper cleanup of WebDriver resources after test execution.

## Key Components

### 1. ThreadLocal<WebDriver>

- Maintains a separate WebDriver instance for each thread
- Prevents thread interference in parallel test execution
- Accessed via `getDriver()` method

### 2. getDriver() Method

- Public static method to access the WebDriver instance
- Implements lazy initialization (creates driver only when needed)
- Returns the existing instance if already created

### 3. createDriver() Method

- Private method that handles WebDriver instantiation
- Supports multiple browsers (Chrome, Firefox)
- Configures browser-specific options
- Sets up implicit waits and window management

### 4. cleanUpDriver() Method

- Properly quits the WebDriver instance
- Removes the thread-local variable to prevent memory leaks
- Should be called in `@After` hooks

## Usage Examples

### Basic Usage

```java
// Get WebDriver instance
WebDriver driver = DriverFactory.getDriver();

// Use the driver
driver.

get("https://example.com");

// Clean up when done
DriverFactory.

cleanUpDriver();
```

### In TestNG/Cucumber Hooks

```java

@After
public void tearDown() {
    DriverFactory.cleanUpDriver();
}
```

## Beginner Level Explanation

### What It Does

- Think of `DriverFactory` as a "car factory" that creates and manages your "browser cars" (WebDriver instances)
- It ensures you always get the right type of browser you need
- Takes care of all the complicated setup behind the scenes

### Key Concepts

1. **Single Instance**: Like having one remote control for your browser
2. **Automatic Setup**: Handles browser driver setup automatically
3. **Cleanup**: Makes sure browsers are properly closed after tests

### When to Use

- At the start of your test to get a browser
- In your test setup methods
- Before any browser interactions

## Expert Level Details

### Design Patterns Used

1. **Singleton Pattern** (through ThreadLocal)
    - Ensures only one WebDriver instance per thread
    - Prevents resource wastage

2. **Factory Method Pattern**
    - Encapsulates object creation logic
    - Makes it easy to add new browser types

### Advanced Configuration

- **Browser Options**: Customize browser behavior through `ChromeOptions`/`FirefoxOptions`
- **Implicit Waits**: Global wait strategy for element location
- **Window Management**: Automatic window maximization

### Thread Safety Considerations

- Uses `ThreadLocal` to handle parallel test execution
- Each thread gets its own WebDriver instance
- Prevents test interference in parallel runs

### Extension Points

1. **Adding New Browsers**:
    - Add new case in `createDriver()`
    - Configure appropriate options

2. **Custom Capabilities**:
    - Extend the method to accept custom capabilities
    - Implement browser-specific configurations

## Best Practices

1. Always call `cleanUpDriver()` in your test teardown
2. Use the factory instead of creating WebDriver instances directly
3. Extend the factory for project-specific needs
4. Keep browser configurations in properties files for better maintainability

## Troubleshooting

- **Browser Not Starting**: Check WebDriverManager setup and browser compatibility
- **Thread Issues**: Ensure proper cleanup in `@After` methods
- **Configuration**: Verify browser options if experiencing unexpected behavior
