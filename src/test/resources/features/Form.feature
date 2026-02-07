Feature: Form scernarios

  Background:
    Given I navigate to "https://demoqa.com/automation-practice-form"
    
    @Form1
    Scenario: Fill the form with valid data
     When I enter first name
     And I enter last name
     And I enter email
     And I select gender
     And I enter mobile number
     And I enter date of birth
     And I select hobbies
     And I enter current address
     Then I submit the form
     
