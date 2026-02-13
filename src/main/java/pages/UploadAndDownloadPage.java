package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IUploadAndDownload;
import utils.ScrollUtils;

/**
 * Page object for the Upload and Download demo section.
 *
 * <p>This class provides simple helpers to interact with the download button,
 * perform file uploads, and verify that uploaded files are shown. It uses
 * explicit waits to synchronize actions and a ScrollUtils helper to ensure
 * elements are visible before interaction.</p>
 */
public class UploadAndDownloadPage implements IUploadAndDownload {

	// Public driver to allow reuse in tests if necessary
	public WebDriver driver;
	// Explicit wait used for element visibility/clickability checks
	public WebDriverWait wait;

	// Button that triggers a file download
	@FindBy(id = "downloadButton")
	private WebElement downloadButton;

	// File input element used to upload a file via sendKeys(filePath)
	@FindBy(id = "uploadFile")
	private WebElement uploadFile;

	// Link to navigate to the Upload & Download demo from the Elements dropdown
	@FindBy(xpath = "//*[text()=\"Elements\"]")
	private WebElement elementsDropDown;

	// Link/tab inside Elements which navigates to the Upload and Download page
	@FindBy(xpath = "//*[text()=\"Upload and Download\"]")
	private WebElement uploadAndDownloadLink;

	// Element that displays the uploaded file path after a successful upload
	@FindBy(id = "uploadedFilePath")
	public WebElement uploadedFilepath;

	/**
	 * Constructor - initializes PageFactory elements and an explicit wait.
	 *
	 * @param driver the WebDriver instance used for interactions
	 */
	public UploadAndDownloadPage(WebDriver driver) {
		this.driver = driver;
		// Configure a 10 second explicit wait for stable interactions
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// --- Actions ---

	/**
	 * Scrolls the page until the download button is visible.
	 *
	 * This method waits for visibility before scrolling to avoid trying to scroll
	 * to an element that isn't yet in the DOM or rendered.
	 */
	public void scrollToDownloadButton() {

		wait.until(ExpectedConditions.visibilityOf(downloadButton));
		// Use the shared ScrollUtils to ensure consistent scrolling behavior
		ScrollUtils.scrollToElement(driver, downloadButton);
		// Alternative (commented) JavaScript scroll kept for reference:
		// ((org.openqa.selenium.JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", downloadButton);
	}

	/**
	 * Clicks the download button to start a file download.
	 */
	public void clickDownloadButton() {
		wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
		downloadButton.click();
	}

	/**
	 * Scrolls the page to the upload input element.
	 */
	public void scrollToUploadFile() {
		wait.until(ExpectedConditions.visibilityOf(uploadFile));
		// Use JS executor directly here to ensure the input is scrolled into view
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				uploadFile);
	}

	/**
	 * Uploads a file by sending the absolute local file path to the hidden file input.
	 *
	 * @param filePath absolute path to the file to upload
	 */
	public void uploadFile(String filePath) {
		wait.until(ExpectedConditions.visibilityOf(uploadFile));	
		ScrollUtils.scrollToElement(driver, uploadFile);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].style.display='block';", uploadFile);

		uploadFile.sendKeys(filePath);
		System.out.println("File uploaded with path: " + filePath);
	}

	/**
	 * Clicks the Elements dropdown in the left navigation to reveal its links.
	 */
	public void clickOnElementsDropDown() {
		wait.until(ExpectedConditions.elementToBeClickable(elementsDropDown));
		elementsDropDown.click();
	}

	/**
	 * Clicks the Upload and Download link inside the Elements section to navigate to the page.
	 */
	public void clickOnUploadAndDownloadTab() {
		wait.until(ExpectedConditions.elementToBeClickable(uploadAndDownloadLink));
		uploadAndDownloadLink.click();
	}

	/**
	 * Verifies whether the uploaded file is shown on the page.
	 *
	 * @return true if the uploaded file path is visible and not empty; false otherwise
	 */
	public boolean verifyUploadedFile() {
		wait.until(ExpectedConditions.visibilityOf(uploadedFilepath));
		// The element should be visible and contain a non-empty path after upload
		return uploadedFilepath.isDisplayed() && !uploadedFilepath.getText().isEmpty();
	}

}