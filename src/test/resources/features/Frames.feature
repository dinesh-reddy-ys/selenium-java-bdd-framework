Feature: Switch to frame functionally

@Frame
Scenario: user switch to frame
  Given I navigate to "https://demoqa.com/frames"
  When I switch to frame1
  Then I get the text