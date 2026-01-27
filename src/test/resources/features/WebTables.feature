#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Web Tables

  Background:
    Given I navigate to "https://demoqa.com/webtables"

  @Table
  Scenario: Read data from web table
    When I read data from web table
  @Test
  Scenario: Add a new employee successfully
    When the user clicks on the add button
    And the user enters valid employee details
    | firstName | lastName | age | email            | salary | department |
    | John      | Doe      | 30  | john.deo@test.com| 50000  | QA         |
    And user submits the form
    Then the new employee record should be displayed in the table
    | firstName | lastName|
    | John      | Doe     |


