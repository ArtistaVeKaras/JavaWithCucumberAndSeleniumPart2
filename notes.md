// Resources
chromeOptions.addArguments("--remote-allow-origins=*");

Exercise notes
webdriver
webdriver123


mvn test -Dcucumber.features="**/Contact_*.feature"

// Run Tests
mvn clean test

// Run test from the terminal with multiple tags
mvn test -Dcucumber.filter.tags="@smoke and @regression"