@regression

Feature: Webdriver University - Contact Us Page

  @smoke
  Scenario: Verify Contact Us Page
    Given I navigate to Webdriver University Contact Us Page
    When I enter a unique first name
    And I enter a unique last name
    And I enter a unique email
    And I enter a unique comment
    And I click on Submit button
    Then I should see a thank you message

  @regression
  Scenario: Verify Contact Us Page - Specific Data
    Given I navigate to Webdriver University Contact Us Page
    When I enter a specific first name akiira
    When I enter a specific last name corr
    When I enter a specific email address akiira_corr@blogs.com
    When I enter a specific comment "How are you?"
    And I click on Submit button
    Then I should see a thank you message