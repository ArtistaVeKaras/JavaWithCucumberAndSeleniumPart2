@Login
Feature: Login Page - WebDriver University

  Background:
    Given I navigate to the Log in page


  Scenario: Login to WebDriver University page Successful
    When I enter my username webdriver
    And I enter my password webdriver123
    And I click the submit button
    Then I should see the following validation message validation succeeded

  Scenario: Login to WebDriver University page unsuccessful
    When I enter my username Bob
    And I enter my password webdriver125
    And I click the submit button
    Then I should see the following validation message validation failed

  Scenario Outline: Validate Success and Unsuccess Login
    When I enter my username <username>
    And I enter my password <password>
    And I click the submit button
    Then I should see the following validation message <loginValidationMessage>

    Examples:
      | username  | password     | loginValidationMessage |
      | webdriver | webdriver123 | validation succeeded   |
      | webdriver | webdriver125 | validation failed      |
      | webdri12r | webdriverooo | validation failed    |

