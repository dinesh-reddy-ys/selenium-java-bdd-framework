package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IUploadAndDownload;

public class UploadAndDownloadPage implements IUploadAndDownload {

	public WebDriver driver;
	public WebDriverWait wait;
	@FindBy(id = "downloadButton")
	private WebElement downloadButton;

	@FindBy(id = "uploadFile")
	private WebElement uploadFile;

	public UploadAndDownloadPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	
	// Actions
	
	public void scrollToDownloadButton() {
		
		wait.until(ExpectedConditions.visibilityOf(downloadButton));
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadButton);
	}
	
	public void clickDownloadButton() {
		wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
		downloadButton.click();
	}
	
	public void uploadFile(String filePath) {
		wait.until(ExpectedConditions.elementToBeClickable(uploadFile));
		uploadFile.sendKeys(filePath);
	}
	
	

}
