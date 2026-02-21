Feature: Search action
# @Order002
# Scenario: search for an item
# Given I launch the url
# When I search for a item
# Then select the item from search list
# @Order001
# Scenario Outline: Login with valid credentials from excel
# Given user logs in with valid username "<username>" and password "<password>"
# Then user should see the dashboard
# 
# Examples:
# |username | password|
# |dummy    | dummy   |

  Scenario: Search for an iphone
    Given I navigate to "https://www.amazon.in/"
    When I search for "iphone"
    Then I verify that all search results contains "iphone"
  @Flipkart
  Scenario: select items with prices less than 50000
    Given I navigate to "https://www.amazon.in/"
    When I search for "iphone"
    Then I select items with prices less than 50000

  Scenario: click on next page button and get items with prices less than 50000
    Given I navigate to "https://www.amazon.in/"
    When I search for "iphone"
    And I click on the next page button
    Then I select items with prices less than 50000
