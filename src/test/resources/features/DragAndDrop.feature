Feature: Drag and drop

  Background:
    Given I navigate to "https://demoqa.com/droppable"

  @Drop
  Scenario: perform drag and
    When I perform drag and drop
    Then I verify the text "Dropped!" is displayed
