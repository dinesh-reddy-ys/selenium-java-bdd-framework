Feature: Search action
  Scenario: search for an item
    Given I launch the url
    When I search for a item
    Then select the item from search list