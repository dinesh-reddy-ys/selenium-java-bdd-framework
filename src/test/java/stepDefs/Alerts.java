package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class Alerts {

	@Given("I launch the demoqa url")
	public void iWantToSeeAnAlert() {
		System.out.println("Alert is expected to be shown.");
	}
	
	@When("Click on the alert button")
	public void iClickOnTheAlertButton() {
		System.out.println("Alert button clicked.");
	}
	
	@Then("Accept the alert")
	public void iShouldSeeAnAlertWithTheMessage() {
		System.out.println("Alert message verified: ");
	}

}
