# Dependency Injection in Test Automation

## What is Dependency Injection?

Dependency Injection (DI) is a design pattern where a class receives its dependencies from an external source rather than
creating them itself. This promotes loose coupling and makes code more maintainable and testable.

## Example from Our Codebase

In our `Login_Steps` class, we use constructor injection:

```java
public class Login_Steps extends BasePageObject {
    private final LoginPageObject loginPageObject;

    public Login_Steps(LoginPageObject loginPageObject) {
        this.loginPageObject = loginPageObject;
    }
    // ... rest of the class
}
```

## Key Benefits

1. **Testability**
   - Easily pass mock objects for testing
   - Isolate components during unit testing

2. **Loose Coupling**
   - Classes don't need to know how to create their dependencies
   - Dependencies can be swapped without changing the dependent class

3. **Maintainability**
   - Easier to modify or extend functionality
   - Clearer dependencies between components

## Before vs After DI

### Before (Tight Coupling)
```java
private final LoginPageObject loginPageObject = new LoginPageObject();
```

### After (Using DI)
```java
private final LoginPageObject loginPageObject;

public Login_Steps(LoginPageObject loginPageObject) {
    this.loginPageObject = loginPageObject;
}
```

## How It Works in Our Framework

1. The test framework (Cucumber with a DI container like PicoContainer) creates and manages the `LoginPageObject` instance
2. When a new `Login_Steps` instance is needed, the framework automatically provides the required dependency
3. This happens automatically based on the constructor parameters

## Best Practices

1. **Use Constructor Injection** for required dependencies
2. **Make dependencies final** when possible to ensure they're set only once
3. **Keep constructors simple** - they should only assign parameters to fields
4. **Document dependencies** - make it clear what each class requires to function

## Further Reading

- [Dependency Injection Principles](https://www.martinfowler.com/articles/injection.html)
- [Dependency Injection in Testing](https://www.baeldung.com/java-spring-dependency-injection-testing)
