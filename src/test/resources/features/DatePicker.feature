Feature: Test Date Picker

  Background:
  Given I navigate to "https://demoqa.com/date-picker"
  
  @DatePicker
  Scenario: Enter new date in date picker input
     When I enter date "10/15/2023" in date picker input
     Then The date picker input should have value "10/15/2023"
