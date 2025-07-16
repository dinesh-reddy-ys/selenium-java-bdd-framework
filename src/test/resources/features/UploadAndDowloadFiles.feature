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
Feature: Upload and download feature

Background: Navigate to logon screen and login with valid credentials
 Given I navigate to "https://demoqa.com/login"
 When I enter valid username "james" and password " Password5" and login
 Then I should be able to login

  @Smoke
  Scenario: Download the file and verify the file file location
    Given I want to click on elements dropdown
    And I want to select upload and download tab
    When I click on the download button
    Then I verify the file is downloaded successfully
 @Download
  Scenario: Download file and verify
  Given I navigate to "https://demoqa.com/upload-download"
  When I click on the download button
  Then I verify the file is downloaded successfully
  @Upload
  Scenario: Upload file and verify
  Given I navigate to "https://demoqa.com/upload-download"
  When I upload the file with path "C:\\Users\\Cheth\\OneDrive\\Documents\\Dinesh\\Resume\\selenium and restAssured(IBM)\\Dinesh_YS_AutomationEngineer_Resume.docx"
  Then I Verify the uploaded file path is displayed
