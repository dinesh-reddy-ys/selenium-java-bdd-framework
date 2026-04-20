package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressBarPage {
	WebDriver driver;
	WebDriverWait wait;
	
	
	@FindBy(id = "startStopButton")
	private WebElement startStopButton;
	
	@FindBy(xpath = "//div[@id='progressBar']/div")
	private WebElement progressBar;
	
	@FindBy(id = "resetButton")
	private WebElement resetButton;
	
	public ProgressBarPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void clickStartStopButton() {
		wait.until(ExpectedConditions.elementToBeClickable(startStopButton));
		startStopButton.click();
	}
	
	public int getProgressBarValue() {
		wait.until(ExpectedConditions.visibilityOf(progressBar));
		String val = progressBar.getText().replace("%", "").trim();
		val = val.isEmpty() ? "0" : val; // Handle empty text case
		return Integer.parseInt(val);
	}
	
	public void clickResetButton() {
		wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
	}
	
	
	
	

}
