package stepDefs;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import pages.HomePage;
import utils.DriverFactory;

/**
 * Step definitions for interacting with the application home page (cards).
 *
 * <p>These steps are lightweight and delegate actual interactions to the
 * {@link HomePage} page object. Keeping steps thin centralizes Selenium
 * logic in the page object and makes feature files expressive.</p>
 */
public class HomeStepDefs {
	// Fix logger to reference this class (previously referenced FormStepDefs)
	private static final Logger logger = LoggerFactory.getLogger(HomeStepDefs.class);
	static {
		// Ensure consistent logging using the project's logback configuration
		System.setProperty("logback.configurationFile", "logback.xml");
	}
	WebDriver driver;
	HomePage homePage;

	/**
	 * Constructor initializes the shared driver and the HomePage page object.
	 */
	public HomeStepDefs() {
		this.driver = DriverFactory.getDriver();
		homePage = new HomePage(driver);
	}

	/**
	 * Clicks the 'Books Store Application' card on the home page to navigate
	 * to the books section.
	 */
	@And("I click on books store application card")
	public void i_click_on_books_store_application_card() {
		homePage.clickBooksStoreApplicationCard();
		logger.info("Clicked on Books Store Application card");
	}

	/**
	 * Clicks the 'Elements' card on the home page to navigate to the
	 * Elements section.
	 */
	@And("I click on elements card")
	public void i_click_on_elements_card() {
		homePage.clickElementsCard();
		logger.info("Clicked on Elements card");
	}

}