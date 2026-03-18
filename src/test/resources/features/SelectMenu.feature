Feature: Select menu

  Scenario: Select a dropdown and an option
    Given I navigate to "https://demoqa.com/select-menu"
    When I select a option from dropdown

  @Multi
  Scenario: Select multiple values
    Given I navigate to "https://demoqa.com/select-menu"
    When I select multiple values from dropdown
