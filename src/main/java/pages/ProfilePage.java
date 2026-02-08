package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IProfilePage;

/**
 * Page object representing the user Profile page.
 *
 * <p>This class provides simple interactions with the profile area such as
 * verifying the displayed username and logging out. It uses an explicit
 * {@link WebDriverWait} to wait for visibility and clickability to improve
 * test reliability.</p>
 */
public class ProfilePage implements IProfilePage {
	WebDriver driver;
	WebDriverWait wait;

	// Label element showing the currently logged-in user's name
	@FindBy(id = "userName-label")
	private WebElement profileName;
	// Button used to log out from the profile page
	@FindBy(id = "submit")
	private WebElement logoutButton;

	/**
	 * Constructor - initialize the WebDriver, wait helper and PageFactory elements.
	 *
	 * @param driver the WebDriver instance used to interact with the page
	 */
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		// 10 second explicit wait for element conditions
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify if the profile name label is displayed on the profile page.
	 *
	 * @return true when the profile label is visible; false otherwise
	 */
	public boolean profileNameIsDisplayed() {
		// Wait until the profile name element is visible before checking
		wait.until(ExpectedConditions.visibilityOf(profileName));
		return profileName.isDisplayed();
	}

	/**
	 * Clicks the logout button to sign the user out.
	 *
	 * The method waits for the button to be clickable to avoid timing issues
	 * with overlays or disabled states.
	 */
	public void clickOnLogoutButton() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
	}

}