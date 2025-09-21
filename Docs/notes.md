// Resources
chromeOptions.addArguments("--remote-allow-origins=*");

Exercise notes
webdriver
webdriver123

// running this command will implement the new changes in your code
mvn test

// Run Tests
mvn clean test

// Run test from the terminal with multiple tags
mvn test -Dcucumber.filter.tags="@smoke and @regression"

// Run features using a specific runner class (recommended for more control)
mvn test -Dtest=MainRunner

// Run features using a specific tags
mvn test -Dcucumber.filter.tags="@smoke"

// Run features using a specific feature file
mvn test -Dcucumber.features="**/Login.feature"

//Run with parallel execution (if configured in your runner)
mvn test -Dcucumber.execution.parallel.enabled=true
