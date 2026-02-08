package stepDefs;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import pages.DynamicPage;
import utils.DriverFactory;
import utils.ExtentManager;

/**
 * Step definitions for the dynamic elements demo page. These steps are thin
 * wrappers that delegate actual browser interactions to the {@link DynamicPage}
 * page object. Keeping steps lightweight improves feature readability and
 * centralizes Selenium actions in page objects.
 */
public class DynamicElementsStepDefs {

	private static final Logger logger = LoggerFactory.getLogger(DynamicElementsStepDefs.class);
	ExtentTest test;
	public WebDriver driver;
	public DynamicPage dynamicPage;
	static {
		// Ensure consistent logging using the project's logback configuration
		System.setProperty("logback.configurationFile", "logback.xml");
	}

	/**
	 * Constructor initializes shared driver and page objects used by the steps.
	 * DriverFactory.getDriver() is expected to return the configured WebDriver
	 * instance managed by the test runner.
	 */
	public DynamicElementsStepDefs() {
		this.driver = DriverFactory.getDriver();
		dynamicPage = new DynamicPage(driver);
	}

	/**
	 * Navigates to the dynamic page using the provided URL. Uses the page
	 * object's navigation method and logs the navigation for reporting.
	 *
	 * @param url the full URL to navigate to
	 */
	@Given("I navigate to the dynamic page with URL {string}")
	public void i_navigate_to_the_dynamic_page_with_URL(String url) {
		// Use the page object rather than creating a new local instance to
		// preserve any shared state on the existing dynamicPage field.
		dynamicPage.navigateToDynamicPage(url);
		if (test != null) {
			test.info("Navigated to the dynamic page with URL: " + url);
		}
		logger.info("Navigated to the dynamic page with URL: " + url);
	}

	/**
	 * Scrolls (if necessary) to the dynamic element and retrieves its text.
	 * The returned text is logged to stdout and to the test/reporting objects.
	 */
	@Given("I get the text from the dynamic element")
	public void i_get_the_text_from_the_dynamic_element() {
		// Ensure the dynamic element is visible by scrolling to it
		dynamicPage.scrollToDynamicElement();
		String dynamicText = dynamicPage.getDynamicElementText();
		// Print and log the retrieved text for debugging and reporting
		System.out.println("Dynamic Element Text: " + dynamicText);
		if (test != null) {
			test.info("Retrieved text from the dynamic element: " + dynamicText);
		}
		logger.info("Retrieved text from the dynamic element: " + dynamicText);
	}
}