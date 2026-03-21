Feature: Select menu

  Scenario: Select a dropdown and an option
    Given I navigate to "https://demoqa.com/select-menu"
    When I select a option from dropdown

  @Multi
  Scenario: Select multiple values
    Given I navigate to "https://demoqa.com/select-menu"
    When I select multiple values from dropdown

  @Old
  Scenario Outline: Select a old style dropdown and select a value
    Given I navigate to "https://demoqa.com/select-menu"
    When I select "<option>" from old style dropdown

    Examples:
      | option  |
      | Red     |
      | Green   |
      | Blue    |
      | Yellow  |
      | Black   |
      | White   |
      | Voilet  |
      | Indigo  |
      | Magenta |
      | Aqua    |

  @MultiSelectDropdown
  Scenario: Select multiselect dropdown and verify it
    Given I navigate to "https://demoqa.com/select-menu"
    When I click on multiselect dropdown
    Then dropdown options should be visible

  @Selected
  Scenario: Select a value from multiselect dropdown and verify it
    Given I navigate to "https://demoqa.com/select-menu"
    When I select "Blue" from multiselect dropdown
    Then "Blue" should be selected in multiselect dropdown

  @Remove
  Scenario: Select a value from multiselect dropdown and remove it
    Given I navigate to "https://demoqa.com/select-menu"
    When I click on multiselect dropdown
    And I select "Blue" from multiselect dropdown
    And I remove "Blue" from multiselect dropdown
    Then "Blue" should not be selected in multiselect dropdown

  @MultipleSelect
  Scenario: Select multiple values from multiselect dropdown and remove one value
    Given I navigate to "https://demoqa.com/select-menu"
    When I click on multiselect dropdown
    And I select "Blue" from multiselect dropdown
    And I select "Red" from multiselect dropdown
    And I remove "Blue" from multiselect dropdown
    Then "Blue" should not be selected in multiselect dropdown
    And "Red" should be selected in multiselect dropdown
