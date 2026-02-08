package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IHomePage;

import java.time.Duration;

/**
 * Page object representing the application's Home page.
 *
 * <p>This class exposes high-level actions a test can perform on the Home page,
 * such as navigating to different demo sections (Elements, Forms, Widgets, etc.).
 * Each action waits for the corresponding card/button to be clickable to avoid
 * timing issues.</p>
 */
public class HomePage implements IHomePage {

	// WebDriver instance used for browser interactions
	private WebDriver driver;

	// Explicit wait used to wait for element states (visibility/clickability)
	private WebDriverWait wait;

	// Card elements on the home page that navigate to different application sections
	@FindBy(xpath = "//*[text() = \"Elements\"]")
	private WebElement elementsCard;

	@FindBy(xpath = "//*[text() = \"Forms\"]")
	private WebElement formsCard;

	@FindBy(xpath = "//*[text() = \"Alerts, Frame & Windows\"]")
	private WebElement alertsFrameWindowsCard;

	@FindBy(xpath = "//*[text() = \"Widgets\"]")
	private WebElement widgetsCard;

	@FindBy(xpath = "//*[text() = \"Interactions\"]")
	private WebElement interactionsCard;

	@FindBy(xpath = "//*[text() = \"Books Store Application\"]")
	private WebElement booksStoreApplicationCard;

	/**
	 * Constructor - initializes PageFactory elements and explicit wait helper.
	 *
	 * @param driver the WebDriver instance to use for page interactions
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// 10 second explicit wait to reduce flakiness for element interactions
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// --- Actions ---

	/**
	 * Clicks the Elements card to navigate to the Elements section.
	 */
	public void clickElementsCard() {
		// Wait until the card is clickable to avoid overlay/stale element issues
		wait.until(ExpectedConditions.elementToBeClickable(elementsCard));
		elementsCard.click();

	}

	/**
	 * Clicks the Forms card to navigate to the Forms section.
	 */
	public void clickFormsCard() {
		wait.until(ExpectedConditions.elementToBeClickable(formsCard));
		formsCard.click();
	}

	/**
	 * Clicks the Alerts, Frame & Windows card to navigate to that section.
	 */
	public void clickAlertsFrameWindowsCard() {
		wait.until(ExpectedConditions.elementToBeClickable(alertsFrameWindowsCard));
		alertsFrameWindowsCard.click();
	}

	/**
	 * Clicks the Widgets card to navigate to the Widgets section.
	 */
	public void clickWidgetsCard() {
		wait.until(ExpectedConditions.elementToBeClickable(widgetsCard));
		widgetsCard.click();
	}

	/**
	 * Clicks the Interactions card to navigate to the Interactions section.
	 */
	public void clickInteractionsCard() {
		wait.until(ExpectedConditions.elementToBeClickable(interactionsCard));
		interactionsCard.click();
	}

	/**
	 * Clicks the Books Store Application card to navigate to the Books Store.
	 */
	public void clickBooksStoreApplicationCard() {
		wait.until(ExpectedConditions.elementToBeClickable(booksStoreApplicationCard));
		booksStoreApplicationCard.click();
	}

}