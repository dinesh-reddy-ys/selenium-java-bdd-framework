package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IUploadAndDownload;
import utils.ScrollUtils;

public class UploadAndDownloadPage implements IUploadAndDownload {

	public WebDriver driver;
	public WebDriverWait wait;
	@FindBy(id = "downloadButton")
	private WebElement downloadButton;

	@FindBy(id = "uploadFile")
	private WebElement uploadFile;
	@FindBy(xpath = "//*[text()=\"Elements\"]")
	private WebElement elementsDropDown;
	@FindBy(xpath = "//*[text()=\"Upload and Download\"]")
	private WebElement uploadAndDownloadLink;
	@FindBy(id = "uploadedFilePath")
	public WebElement uploadedFilepath;

	public UploadAndDownloadPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	
	// Actions
	
	public void scrollToDownloadButton() {
		
		wait.until(ExpectedConditions.visibilityOf(downloadButton));
		ScrollUtils.scrollToElement(driver, downloadButton);
		//((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadButton);
	}
	
	public void clickDownloadButton() {
		wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
		downloadButton.click();
	}
	
	public void scrollToUploadFile() {
		wait.until(ExpectedConditions.visibilityOf(uploadFile));
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadFile);
	}
	public void uploadFile(String filePath) {
		wait.until(ExpectedConditions.elementToBeClickable(uploadFile));
		uploadFile.sendKeys(filePath);
	}
	
	public void clickOnElementsDropDown() {
		wait.until(ExpectedConditions.elementToBeClickable(elementsDropDown));
		elementsDropDown.click();
	}
	public void clickOnUploadAndDownloadTab() {
		wait.until(ExpectedConditions.elementToBeClickable(uploadAndDownloadLink));
		uploadAndDownloadLink.click();
	}
	
	public boolean verifyUploadedFile() {
		wait.until(ExpectedConditions.visibilityOf(uploadedFilepath));
		return uploadedFilepath.isDisplayed() && !uploadedFilepath.getText().isEmpty();
	}

	

}
