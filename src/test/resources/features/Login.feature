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
Feature: Books store login

#  Background: open the website and navigate to bookstore application
#   Given I navigate to "https://demoqa.com"
#   When I click on books store application card
#   Then I should land on books store page and Login button should be visible
   

  @Login
  Scenario: Login scenario
   Given I navigate to "https://demoqa.com/login"
   When I enter valid username "dreddy" and password "Dinesh@123" and login
   Then I should be able to login


