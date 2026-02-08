package stepDefs;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DatePickerPage;
import utils.DriverFactory;

/**
 * Step definitions for interacting with the date picker demo page.
 *
 * <p>These Cucumber steps provide a thin layer that delegates browser
 * interactions to the {@link DatePickerPage} page object. Keeping steps thin
 * helps keep feature files readable and concentrates Selenium code inside
 * page objects.</p>
 */
public class DatePickerStepDefs {
	private static final Logger logger = LoggerFactory.getLogger(DatePickerStepDefs.class);
	static {
		// Set the Logback configuration to ensure consistent logging
		System.setProperty("logback.configurationFile", "logback.xml");
	}
	ExtentTest test;
	public WebDriver driver;
	public DatePickerPage datePickerPage;

	/**
	 * Constructor initialises the shared WebDriver and page object.
	 * DriverFactory.getDriver() is expected to provide a configured driver
	 * instance managed by the test harness.
	 */
	public DatePickerStepDefs() {
		this.driver = DriverFactory.getDriver();
		datePickerPage = new DatePickerPage(driver);
	}

	/**
	 * Step: Enter the given date string into the date picker input element.
	 * Delegates the actual sendKeys/click sequence to the DatePickerPage.
	 *
	 * @param date the date string to enter (format is validated by the page object)
	 */
	@When("I enter date {string} in date picker input")
	public void i_enter_date_in_the_date_picker(String date) {
		datePickerPage.enterDate(date);
		logger.info(date + " is entered in the date picker");
	}

	/**
	 * Step: Assert that the date picker input contains the expected value.
	 * This reads the value via the page object and performs an exact equality
	 * assertion. On mismatch, an AssertionError is thrown to fail the test.
	 *
	 * @param expectedDate expected date string to compare against the input value
	 */
	@Then("The date picker input should have value {string}")
	public void the_date_picker_input_should_have_value(String expectedDate) {
		String actualDate = datePickerPage.getDate();
		// Provide a short implicit wait before asserting to allow any UI
		// updates to propagate (keeps tests resilient to small timing differences).
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		if (!actualDate.equals(expectedDate)) {
			throw new AssertionError("Expected date: " + expectedDate + ", but got: " + actualDate);
		}
		logger.info("Date picker input has the expected value: " + expectedDate);
	}

}