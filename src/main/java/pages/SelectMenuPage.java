package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectMenuPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "(//div[@class='css-19bb58m'])[1]")
	private WebElement selectValueDropdown;

	@FindBy(xpath = "//div[contains(text(),'Group 1, option 1')]")
	private WebElement selectValueOption1;

	public SelectMenuPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	public void selectValueOption1() {
		wait.until(ExpectedConditions.elementToBeClickable(selectValueDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectValueOption1)).click();
	}

}
