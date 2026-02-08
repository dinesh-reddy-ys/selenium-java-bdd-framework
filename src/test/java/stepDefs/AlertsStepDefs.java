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

/**
 * Cucumber step definitions for interactions with browser alerts on the
 * Alerts demo page. These step methods delegate to the {@link AlertsPage}
 * page-object which contains Selenium interactions and assertions.
 *
 * <p>Keeping step methods thin improves readability of feature files and
 * centralizes Selenium logic inside page objects.</p>
 */
public class AlertsStepDefs {
	private static final Logger logger = LoggerFactory.getLogger(AlertsStepDefs.class);
	ExtentTest test;
	public WebDriver driver;
	public AlertsPage alertsPage;
	static {
		// Set the Logback configuration to ensure consistent logging
		System.setProperty("logback.configurationFile", "logback.xml");
	}

	/**
	 * Constructor initializes the WebDriver and page object instances used by
	 * the step definitions. DriverFactory.getDriver() is expected to return a
	 * shared driver instance configured by the test runner.
	 */
	public AlertsStepDefs() {
		driver = DriverFactory.getDriver();
		alertsPage = new AlertsPage(driver);
	}

	/**
	 * Step: Clicks the simple alert button on the demo page.
	 * Delegates the click action to the AlertsPage page-object.
	 */
	@When("I click on the alert button")
	public void iClickOnTheAlertButton() {
		alertsPage.clickOnAlertButton();

	}

	/**
	 * Step: Accepts the currently shown alert and verifies its message. The
	 * verification logic is implemented in AlertsPage.verifyAndAcceptAlert().
	 */
	@Then("I accept the alert")
	public void iShouldSeeAnAlertWithTheMessage() {
		alertsPage.verifyAndAcceptAlert();
	}

	/**
	 * Step: Clicks the timer-based alert which appears after a short delay.
	 * The page-object handles waiting for the alert to appear.
	 */
	@When("I click on timer alert button")
	public void i_click_on_timer_alert_button() {
		alertsPage.clickOnTimerAlertButton();
	}

	/**
	 * Step: Clicks the confirm dialog trigger on the demo page.
	 */
	@When("I click on confirm button")
	public void i_click_on_confirm_button() {
		alertsPage.clickOnConfirmButton();
	}

	/**
	 * Step: Dismisses a confirm dialog and verifies the dismissal. The
	 * verification lives in AlertsPage.verifyAndDismissAlert().
	 */
	@Then("I dismiss the alert")
	public void i_dismiss_the_alert() {
		alertsPage.verifyAndDismissAlert();
	}

	/**
	 * Step: Clicks the prompt dialog trigger which opens a prompt alert.
	 */
	@When("I click on prompt button")
	public void i_click_on_prompt_button() {
		alertsPage.clickOnPromptButton();
	}

	/**
	 * Step: Enters the provided text into the prompt dialog's input.
	 * The page-object handles switching to the alert and sending keys.
	 *
	 * @param text the text to enter into the prompt dialog
	 */
	@And("I enter text {string}")
	public void i_enter_text(String text) {
		alertsPage.enterPromptText(text);
	}

}