Feature: Webdriver University Contact Us Page

  Scenario: Verify Contact Us Page

    Given I navigate to Webdriver University Contact Us Page
    When I enter a unique first name
    And I enter a unique last name
    And I enter a unique email
    And I enter a unique comment
    And I click on Submit button
    Then I should see a thank you message