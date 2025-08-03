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

  @Table
  Scenario: Read data from web table 
    Given I navigate to "https://demoqa.com/webtables"
    When I read data from web table
  @Table1  
  Scenario Outline: Verify value in web table
    Given I am on the web table page
    When I load the table data
    Then I should see "<value>" in the web table
    
  Examples:
     |value|
     |Alden|
     |Vega|