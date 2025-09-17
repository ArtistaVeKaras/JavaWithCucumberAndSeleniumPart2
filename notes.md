// Resources
chromeOptions.addArguments("--remote-allow-origins=*");

mvn test -Dcucumber.features="**/Contact_*.feature"