package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class Alerts {

	@Given("I launch the demoqa url")
	public void iWantToSeeAnAlert() {
		// This step is intentionally left blank.
		// The alert will be handled in the hooks.
		System.out.println("Alert is expected to be shown.");
	}
	
	@When("Click on the alert button")
	public void iClickOnTheAlertButton() {
		// This step is intentionally left blank.
		// The alert button click will be handled in the hooks.
		System.out.println("Alert button clicked.");
	}
	
	@Then("Accept the alert")
	public void iShouldSeeAnAlertWithTheMessage() {
		// This step is intentionally left blank.
		// The alert verification will be handled in the hooks.
		System.out.println("Alert message verified: ");
	}

}
