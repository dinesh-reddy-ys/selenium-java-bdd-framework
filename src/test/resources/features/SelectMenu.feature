Feature: Select menu

  Scenario: Select a dropdown and an option
    Given I navigate to "https://demoqa.com/select-menu"
    When I select a option from dropdown

  @Multi
  Scenario: Select multiple values
    Given I navigate to "https://demoqa.com/select-menu"
    When I select multiple values from dropdown

  @Old
  Scenario Outline: select a old style dropdown and select a value
    Given I navigate to "https://demoqa.com/select-menu"
    When I select "<option>" from old style dropdown

    Examples:
      | option |
      | Red    |
      | Green  |
      | Blue   |
      | Yellow |
      |  Black |
      | White  |
      | Voilet |
      | Indigo |
      | Magenta|
      | Aqua   |