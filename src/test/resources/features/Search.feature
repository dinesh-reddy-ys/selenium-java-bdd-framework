Feature: Search action
#  @Order002
#  Scenario: search for an item
#    Given I launch the url
#    When I search for a item
#    Then select the item from search list
#  @Order001
#  Scenario Outline: Login with valid credentials from excel
#    Given user logs in with valid username "<username>" and password "<password>"
#    Then user should see the dashboard
#
#  Examples:
#    |username | password|
#    |dummy    | dummy   |
@Flipkart
Scenario: Search for an iphone
 Given I navigate to "https://www.amazon.in/"
 When I search for "iphone"
 Then I verify that all search results contains "iphone"