Feature: Selectable
 
 @Selectable
  Scenario: Select any two value from listr view
    Given I navigate to "https://demoqa.com/selectable"
    When I select below values
    |Cras justo odio |
    |Dapibus ac facilisis in |
    Then I should see the selected values in the list
    |Cras justo odio |
    |Dapibus ac facilisis in |
    
    @SelectableGrid
    Scenario:  Select any two values from grid view
    Given I navigate to "https://demoqa.com/selectable"
    When I click on "grid" tab
    And I select below values
    |One |
    |Two |
    Then I should see the selected values in the list
    |One |
    |Two |
    
    
    
