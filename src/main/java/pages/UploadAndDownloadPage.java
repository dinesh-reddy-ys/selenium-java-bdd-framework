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

    // WebDriver instance to interact with the browser
    public WebDriver driver;

    // WebDriverWait instance for explicit waits
    public WebDriverWait wait;

    // WebElement for the "Download" button
    @FindBy(id = "downloadButton")
    private WebElement downloadButton;

    // WebElement for the "Upload File" input field
    @FindBy(id = "uploadFile")
    private WebElement uploadFile;

    // WebElement for the "Elements" dropdown
    @FindBy(xpath = "//*[text()=\"Elements\"]")
    private WebElement elementsDropDown;

    // WebElement for the "Upload and Download" link
    @FindBy(xpath = "//*[text()=\"Upload and Download\"]")
    private WebElement uploadAndDownloadLink;

    // WebElement to display the uploaded file path
    @FindBy(id = "uploadedFilePath")
    public WebElement uploadedFilepath;

    // Constructor to initialize the UploadAndDownloadPage with WebDriver and WebDriverWait
    public UploadAndDownloadPage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // Scrolls to the "Download" button and ensures it is visible
    public void scrollToDownloadButton() {
        wait.until(ExpectedConditions.visibilityOf(downloadButton));
        ScrollUtils.scrollToElement(driver, downloadButton);
    }

    // Clicks on the "Download" button
    public void clickDownloadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        downloadButton.click();
    }

    // Scrolls to the "Upload File" input field and ensures it is visible
    public void scrollToUploadFile() {
        wait.until(ExpectedConditions.visibilityOf(uploadFile));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadFile);
    }

    // Uploads a file by providing the file path to the "Upload File" input field
    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.elementToBeClickable(uploadFile));
        uploadFile.sendKeys(filePath);
    }

    // Clicks on the "Elements" dropdown
    public void clickOnElementsDropDown() {
        wait.until(ExpectedConditions.elementToBeClickable(elementsDropDown));
        elementsDropDown.click();
    }

    // Clicks on the "Upload and Download" tab
    public void clickOnUploadAndDownloadTab() {
        wait.until(ExpectedConditions.elementToBeClickable(uploadAndDownloadLink));
        uploadAndDownloadLink.click();
    }

    // Verifies if the uploaded file is displayed and the file path is not empty
    public boolean verifyUploadedFile() {
        wait.until(ExpectedConditions.visibilityOf(uploadedFilepath));
        return uploadedFilepath.isDisplayed() && !uploadedFilepath.getText().isEmpty();
    }
}
