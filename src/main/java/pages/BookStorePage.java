package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IBookStorePage;

public class BookStorePage implements IBookStorePage {
	WebDriver driver;
	WebDriverWait wait;
	
	// Elements
	@FindBy(xpath = "//button[text() = \"Login\"]")
	private WebElement loginButton;
	
	
	// Constructor to initialize WebDriver
	public BookStorePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	
	// Actions
	public void clickOnLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}
	
	public boolean isLoginButtonDisplayed() {
		try {
			wait.until(ExpectedConditions.visibilityOf(loginButton));
			return loginButton.isDisplayed();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
