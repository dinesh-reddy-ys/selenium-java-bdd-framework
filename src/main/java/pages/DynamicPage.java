package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicPage {

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//*[@class=\"text-center\"]/following-sibling::p")
	private WebElement dynamicElement; 

	
	public DynamicPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
	    PageFactory.initElements(driver, this);
	
	}

	/**
	 * Navigate to the specified URL.
	 * 
	 * @param url - The URL to navigate to.
	 */

	public void navigateToDynamicPage(String url) {
		driver.get(url);
	}
	
	// Scroll to the dynamic element
	public void scrollToDynamicElement() {
		// Wait for the dynamic element to be present
		wait.until(driver -> dynamicElement.isDisplayed());
		// Scroll to the dynamic element
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dynamicElement);
	}

	// Get text from dynamic element
	public String getDynamicElementText() {
		// Wait for the dynamic element to be present
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Get the text from the dynamic element
		return dynamicElement.getText();
	}
}
