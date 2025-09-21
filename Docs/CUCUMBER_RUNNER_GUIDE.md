# Cucumber Runner Class Guide

## Table of Contents
- [What is a Test Runner?](#what-is-a-test-runner)
- [CucumberOptions Explained](#cucumberoptions-explained)
- [Beginner's Guide](#beginners-guide)
- [Expert's Guide](#experts-guide)
- [Best Practices](#best-practices)

## What is a Test Runner?

A Test Runner in Cucumber is a class that provides the entry point for executing your feature files. 
It's the bridge between your feature files (written in Gherkin) and the step definitions (written in Java).

## CucumberOptions Explained

Looking at our `MainRunner.java`, here's what each option means:

```java
@CucumberOptions(
    features = {"classpath:features"},  // Path to feature files
    glue = {"stepDefinitions"},        // Package with step definitions
    tags = "@Login",                   // Tags to filter scenarios
    monochrome = true,                 // Readable console output
    dryRun = false,                    // Check if all steps have definitions
    plugin = {                         // Report configurations
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
    }
)
```

## Beginner's Guide

### 1. Basic Concepts
- **Test Runner**: The starting point of your Cucumber tests.
- **Feature Files**: `.feature` files containing scenarios in plain English.
- **Step Definitions**: Java methods that implement the steps in your scenarios.
- **Hooks**: Special methods that run before/after scenarios.

### 2. Understanding the Runner
- The `@CucumberOptions` annotation configures how your tests will run.
- `features`: Points to your `.feature` files.
- `glue`: Tells Cucumber where to find your step definitions.
- `tags`: Lets you run specific scenarios by tagging them with `@tagName`.

### 3. Running Tests
- Right-click the Runner class and select "Run".
- Use `mvn test` from the command line.
- View the HTML report in the `target/cucumber-reports` folder.

## Expert's Guide

### 1. Advanced Configuration
- **Parallel Execution**: Use `cucumber-junit-platform-engine` for parallel test execution.
- **Custom Plugins**: Implement custom formatters by extending `io.cucumber.plugin.Plugin`.
- **Parameter Types**: Create custom parameter types for complex data structures.

### 2. Performance Optimization
- Use `dryRun = true` to quickly check for undefined steps.
- Implement `@Before` and `@After` hooks for test setup/teardown.
- Use `@BeforeStep` and `@AfterStep` for actions between steps.

### 3. Integration with Build Tools
- **Maven**: Configure in `pom.xml` with `maven-surefire-plugin`.
- **Gradle**: Use the `cucumber-java` and `cucumber-junit` dependencies.

## Best Practices

1. **Organize Features**: Group related scenarios in feature files.
2. **Use Tags Wisely**: Tag scenarios for different test suites (e.g., `@smoke`, `@regression`).
3. **Keep Step Definitions DRY**: Reuse step definitions when possible.
4. **Meaningful Reports**: Configure multiple report formats (HTML, JSON, JUnit).
5. **Error Handling**: Implement proper error handling in step definitions.
6. **Configuration Management**: Use property files for environment-specific configurations.

## Example Commands

Run all tests:
```bash
mvn test
```

Clean and run all tests:
```bash
mvn clean test
```

Run tests with specific tags (AND condition):
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

Run tests with a specific runner class:
```bash
mvn test -Dtest=MainRunner
```

Run tests with a specific tag:
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

Run a specific feature file:
```bash
mvn test -Dcucumber.features="**/Login.feature"
```

Run tests in parallel (requires configuration in runner):
```bash
mvn test -Dcucumber.execution.parallel.enabled=true
```

## Troubleshooting

- **No tests found**: Check your `@CucumberOptions` paths.
- **Step definitions not found**: Verify the `glue` path is correct.
- **Reports not generating**: Check file permissions and output paths.

For more information, refer to the [official Cucumber documentation](https://cucumber.io/docs/guides/).
