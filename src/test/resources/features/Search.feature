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

  Background: User is on amazon home page
    Given I navigate to "https://www.amazon.in/"

  Scenario: Search for an iphone
    When I search for "iphone"
    Then I verify that all search results contains "iphone"

  @Flipkart
  Scenario: select items with prices less than 50000
    When I search for "iphone"
    Then I select items with prices less than 50000

  Scenario: click on next page button and get items with prices less than 50000
    When I search for "iphone"
    And I click on the next page button
    Then I select items with prices less than 50000

  @amazon1
  Scenario: click on add to cart and delete the item from cart
    When I search for "iphone"
    And add first item to the cart
    Then delete the item from the cart in the same page

  @amazon
  Scenario: click on item using name of the product
    When I search for "iphone"
    And I click on the item with name "iPhone 17 Pro 256 GB"
    And I switch to the new window
    Then I verify that the item name is "iPhone 17 Pro 256 GB"
