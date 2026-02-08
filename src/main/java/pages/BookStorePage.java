package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IBookStorePage;

/**
 * Page object for the Book Store page.
 *
 * <p>This class provides interactions related to the Book Store section of the demo
 * application. It follows the Page Object Model pattern: fields are located using
 * @FindBy and actions are exposed as simple methods. The implementation uses an
 * explicit {@link WebDriverWait} to wait for element visibility and clickability
 * to make interactions more reliable.</p>
 */
public class BookStorePage implements IBookStorePage {
	WebDriver driver;
	WebDriverWait wait;

	// Primary action button on the Book Store page that navigates to the login form
	@FindBy(xpath = "//button[text() = \"Login\"]")
	private WebElement loginButton;

	/**
	 * Constructor - initializes the page's WebDriver and wait helper, and wires
	 * up the @FindBy annotated elements via PageFactory.
	 *
	 * @param driver the WebDriver instance to use for page interactions
	 */
	public BookStorePage(WebDriver driver) {
		this.driver = driver;
		// Use a 10 second explicit wait for element conditions to reduce flakiness
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks the Login button. This method waits until the button is clickable
	 * to avoid ElementNotInteractable or StaleElement exceptions.
	 */
	public void clickOnLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}

	/**
	 * Returns whether the Login button is currently visible on the page.
	 *
	 * <p>The method waits for visibility and returns true if displayed. If any
	 * exception occurs while checking visibility (for example, a timeout or
	 * stale element), the exception is caught and the method returns false.</p>
	 *
	 * @return true if the login button is visible and displayed; false otherwise
	 */
	public boolean isLoginButtonDisplayed() {
		try {
			// Wait for the element to be visible before checking isDisplayed()
			wait.until(ExpectedConditions.visibilityOf(loginButton));
			return loginButton.isDisplayed();
		} catch (Exception e) {
			// Log the exception stack trace for debugging, but do not rethrow; caller
			// can decide how to proceed when false is returned.
			e.printStackTrace();
		}
		return false;
	}

}