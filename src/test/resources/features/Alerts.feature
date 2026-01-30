Feature: Test alerts

  Background:
    Given I navigate to "https://demoqa.com/alerts"

  @Skip
  Scenario: User clicks on alert and accept the alert
    When I click on the alert button
    Then I accept the alert


  Scenario: user clicks on timer alert button and accept the alert
    When I click on timer alert button
    Then I accept the alert

  Scenario: User clicks on confirm button and accept the alert
    When I click on confirm button
    Then I accept the alert

  Scenario: User clicks on confirm button and dismiss the alert
    When I click on confirm button
    Then I dismiss the alert

  Scenario:  User clicks on prompt alert button, enters text and accept the alert
    When I click on prompt button
    And I enter text "Hello"
    Then I accept the alert
