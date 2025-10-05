# Java Selenium WebDriver Automation Framework

This is a test automation framework built with Java, Selenium WebDriver, TestNG, and Cucumber, 
created as part of the Bruno Udemy course on Selenium WebDriver.

## 🚀 Features

- **Page Object Model (POM)** design pattern for better maintainability
- **Cucumber BDD** for writing tests in Gherkin syntax
- **TestNG** test runner and reporting
- **WebDriverManager** for automatic driver management
- Maven for dependency management

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Chrome/Firefox browser installed
- Internet connection (for downloading dependencies)

## 🛠️ Setup

1. Clone the repository:
   ```bash
   git clone [your-repository-url]
   cd Java-Selenium-WebDriver-Bruno-Udemy
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

## 🚦 Running Tests

### Running All Tests

To run all tests using Maven:

```bash
mvn clean test
```

### Running Specific Test Suite

To run tests using TestNG XML:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Running with Maven and Specific Browser

You can specify the browser using the `-Dbrowser` parameter:

```bash
mvn test -Dbrowser=chrome
# or
mvn test -Dbrowser=firefox
```

### Running with Specific Tags

To run tests with specific Cucumber tags:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

## 📁 Project Structure

```
src/
├── main/java/
│   └── pageObjects/      # Page Object classes
│   └── utils/            # Utility classes
└── test/java/
    ├── runners/          # Test runners
    ├── stepDefinitions/  # Step definitions for BDD
    └── features/         # Feature files
```

## 🔧 Configuration

- Browser settings and timeouts can be configured in the `GlobalVariables` class
- Test data can be managed in the respective page object classes or externalized as needed

## 📊 Reports

Test reports are generated in the `target` directory after test execution. For detailed reports, consider integrating with ExtentReports or Allure.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is for educational purposes as part of the Bruno Udemy course.

---

Happy Testing! 🚀
