package stepDefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProgressBarPage;
import utils.DriverFactory;

public class ProgressBarStepDefs {

	public WebDriver driver;
	ProgressBarPage progressBarPage;
	
	public ProgressBarStepDefs() {
		this.driver = DriverFactory.getDriver();
		progressBarPage = new ProgressBarPage(driver); 
	}
	
	@When("I click on the start button")
	public void i_click_on_the_start_button() {
		progressBarPage.clickStartStopButton();
	}
	
	@And("I wait until the progress bar reaches {int} percent")
	public void i_wait_until_the_progress_bar_reaches_percent(Integer expectedValue) {
		int actualValue = 0;
		while( actualValue < expectedValue) {
			actualValue = progressBarPage.getProgressBarValue();
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
				break;
			}
		}
	}
	
	@Then("I click on the stop button")
	public void i_click_on_the_stop_buttton() {
		progressBarPage.clickStartStopButton();
	}
}
