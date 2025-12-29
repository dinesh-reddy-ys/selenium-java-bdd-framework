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

public class LoginPage implements ILoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="userName")
	private WebElement usernameField;
	@FindBy(id="password")
	private WebElement passwordField;
	@FindBy(id="login")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	
	public void clickOnUsernameField() {
		//Scroll to the username field element before clicking
		
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		wait.until(ExpectedConditions.elementToBeClickable(usernameField));
		ScrollUtils.scrollToElement(driver,usernameField);
		usernameField.click();
	}
	
	public void clickOnPasswordField() {
		// Scroll to the password field before clicking
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		passwordField.click();
	}
	public void clickOnLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}
	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.sendKeys(username);
	}
	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		passwordField.sendKeys(password);
	}

}

	


