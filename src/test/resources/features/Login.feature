Feature: Login Page - WebDriver University

  @Login
  Scenario: Login to WebDriver University Successful
    Given I navigate to the Log in page
    When I enter my username webdriver
    And I enter my password webdriver123
    And I click the submit button
    Then I should see the success message validation

  @Login
  Scenario: Login to WebDriver University unsuccessful
    Given I navigate to the Log in page
    When I enter my username webdriver
    And I enter my password webdriver125
    And I click the submit button
    Then I should see the unsuccess message validation