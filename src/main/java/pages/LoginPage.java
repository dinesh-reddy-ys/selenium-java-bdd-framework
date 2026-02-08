package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.ILoginPage;
import utils.ScrollUtils;

/**
 * Page object representing the Login page of the demo application.
 *
 * <p>
 * This class centralizes operations related to logging in: locating username and
 * password inputs, typing values, and clicking the login button. It uses an explicit
 * {@link WebDriverWait} to wait for visibility and clickability to reduce flakiness.
 * ScrollUtils is used to ensure elements are scrolled into view before interaction.
 * </p>
 */
public class LoginPage implements ILoginPage {
	WebDriver driver;
	WebDriverWait wait;

	// Username input field
	@FindBy(id = "userName")
	private WebElement usernameField;

	// Password input field
	@FindBy(id = "password")
	private WebElement passwordField;

	// Login button
	@FindBy(id = "login")
	private WebElement loginButton;

	/**
	 * Constructor - initialize WebDriver, explicit wait and PageFactory elements.
	 *
	 * @param driver WebDriver instance used to drive the browser
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// --- Actions ---

	/**
	 * Scrolls to and clicks the username input to focus it.
	 */
	public void clickOnUsernameField() {
		// Wait until visible and clickable to avoid timing issues
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		// Ensure the element is in view before clicking (prevents overlays)
		ScrollUtils.scrollToElement(driver, usernameField);
		usernameField.click();
	}

	/**
	 * Scrolls to and clicks the password input to focus it.
	 */
	public void clickOnPasswordField() {
		// Wait until visible and click-ready
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		ScrollUtils.scrollToElement(driver, passwordField);
		passwordField.click();
	}

	/**
	 * Clicks the login button after waiting for it to be clickable.
	 */
	public void clickOnLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}

	/**
	 * Enters the username into the username field.
	 *
	 * @param username the username to enter
	 */
	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	/**
	 * Enters the password into the password field.
	 *
	 * @param password the password to enter
	 */
	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		passwordField.clear();
		passwordField.sendKeys(password);
	}

}