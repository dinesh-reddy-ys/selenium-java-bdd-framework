package stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.FramesPage;
import utils.DriverFactory;

/**
 * Step definitions for interacting with nested frames on the Frames demo page.
 *
 * <p>These steps delegate frame-switching and content extraction to the
 * {@link FramesPage} page object so that Selenium logic remains in the page
 * object and steps remain expressive and straightforward.</p>
 */
public class FramesStepDefs {

	// Use the correct class in logger to avoid confusing logs and stack traces
	private static final Logger logger = LoggerFactory.getLogger(FramesStepDefs.class);
	static {
		// Ensure consistent logging using the project's logback configuration
		System.setProperty("logback.configurationFile", "logback.xml");
	}
	public WebDriver driver;
	FramesPage framesPage;

	/**
	 * Constructor initialises shared WebDriver and the FramesPage page object.
	 */
	public FramesStepDefs() {
		this.driver = DriverFactory.getDriver();
		framesPage = new FramesPage(driver);
	}

	/**
	 * Step: Switch the driver's context to the frame with identifier 'frame1'.
	 * The page object's implementation encapsulates the locator and the
	 * switching logic.
	 */
	@When("I switch to frame1")
	public void i_switches_to_frame() {
		framesPage.switchToFrameById();
	}

	/**
	 * Step: Retrieve and (optionally) assert text content present inside the
	 * current frame. The step is intentionally left minimal — the real
	 * extraction/assertion is expected to live in the page object or test
	 * calling code.
	 */
	@Then("I get the text")
	public void i_get_the_text() {
		// Intentionally left blank: add assertions here as needed or
		// delegate to a FramesPage helper that returns the text.
	}
}