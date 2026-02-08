package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IDynamicPage;

/**
 * Page object representing a demo page with dynamic content.
 *
 * <p>
 * This class encapsulates interactions with a dynamically-loaded element on the
 * page. It provides methods to navigate to the page, scroll to the dynamic
 * element, and retrieve its text. The implementation uses an explicit
 * {@link WebDriverWait} for synchronization when waiting for visibility and
 * relies on an implicit wait in one method to tolerate short delays when
 * fetching text.
 * </p>
 */
public class DynamicPage implements IDynamicPage {

	// WebDriver used for driving browser interactions
	private WebDriver driver;

	// Explicit wait helper to wait for conditions like visibility
	private WebDriverWait wait;

	// Locator for the dynamic element; the XPath selects the paragraph that follows
	// an element with class 'text-center'. Adjust the locator if the DOM changes.
	@FindBy(xpath = "//*[@class=\"text-center\"]/following-sibling::p")
	private WebElement dynamicElement;

	/**
	 * Constructor - initializes the WebDriver, explicit wait, and PageFactory
	 * elements for the page object.
	 *
	 * @param driver the WebDriver instance to use
	 */
	public DynamicPage(WebDriver driver) {
		this.driver = driver;
		// Use a 10 second explicit wait for stability; change if the app is slower
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);

	}

	/**
	 * Navigate to the specified URL for the dynamic content page.
	 *
	 * @param url the URL to navigate to
	 */
	public void navigateToDynamicPage(String url) {
		// Direct navigation is simple and reliable for test setup
		driver.get(url);
	}

	/**
	 * Scrolls the page to the dynamic element.
	 *
	 * <p>
	 * This method waits until the element is displayed using the explicit wait (to
	 * avoid timing issues where the element is not yet in the DOM), then scrolls it
	 * into view using JavaScript.
	 * </p>
	 */
	public void scrollToDynamicElement() {
		// Wait for the dynamic element to be displayed before attempting to scroll
		wait.until(driver -> dynamicElement.isDisplayed());
		// Scroll the element into view to ensure it is visible and interactable
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				dynamicElement);
	}

	/**
	 * Retrieves the visible text of the dynamic element.
	 *
	 * <p>
	 * This method sets a short implicit wait to tolerate brief delays while the
	 * element's text is populated. It then returns the text value. Note: callers
	 * should avoid relying on implicit waits globally; this localized use mirrors
	 * the original implementation's intent.
	 * </p>
	 *
	 * @return the text contained within the dynamic element
	 */
	public String getDynamicElementText() {
		// Allow a short implicit wait here in case the text populates shortly
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Return the element's visible text. If the element is not present or
		// visible, this may throw an exception which the caller should handle.
		return dynamicElement.getText();
	}
}