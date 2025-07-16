package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IProfilePage;

public class ProfilePage implements IProfilePage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "userName-value")
	private WebElement profileName;
	@FindBy(id="submit")
	private WebElement logoutButton;
	
	
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Verify if the profile name is displayed on the profile page.
	 */
	public boolean profileNameIsDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(profileName));
		return profileName.isDisplayed();
	}
	
	public void clickOnLogoutButton() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
	}

}
