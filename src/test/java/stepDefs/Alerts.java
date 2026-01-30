package stepDefs;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AlertsPage;
import utils.DriverFactory;
import utils.ExtentManager;

import java.time.Duration;

public class Alerts {
    private static  final Logger logger = LoggerFactory.getLogger(Alerts.class);
	ExtentTest test;
	public WebDriver driver;
	public AlertsPage alertsPage;
	static {
		System.setProperty("logback.configurationFile","logback.xml");
	}

   public Alerts(){
		driver = DriverFactory.getDriver();
		alertsPage = new AlertsPage(driver);
        test = ExtentManager.createTest("Alerts test");
   }

	@When("I click on the alert button")
	public void iClickOnTheAlertButton() {
     alertsPage.clickOnAlertButton();

	}
	
	@Then("I accept the alert")
	public void iShouldSeeAnAlertWithTheMessage() {
     alertsPage.verifyAndAcceptAlert();
	}

	@When("I click on timer alert button")
	public void i_click_on_timer_alert_button(){
		alertsPage.clickOnTimerAlertButton();
	}

	@When("I click on confirm button")
	public void i_click_on_confirm_button(){
		alertsPage.clickOnConfirmButton();
	}
	@Then("I dismiss the alert")
	public void i_dismiss_the_alert(){
		alertsPage.verifyAndDismissAlert();
	}

	@When("I click on prompt button")
	public void i_click_on_prompt_button(){
		alertsPage.clickOnPromptButton();
	}

	@And("I enter text {string}")
	public void i_enter_text(String text){
    alertsPage.enterPromptText(text);
	}



}
