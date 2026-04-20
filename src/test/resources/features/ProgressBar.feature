Feature: Progress bar


@ProgressBar
Scenario: Click on start button and stop at 50 percent
Given I navigate to "https://demoqa.com/progress-bar"
When I click on the start button
And I wait until the progress bar reaches 50 percent
Then I click on the stop button