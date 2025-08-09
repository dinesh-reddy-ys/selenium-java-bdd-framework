package stepDefs;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TabsAndDropdownsPage;
import pages.UploadAndDownloadPage;
import utils.DownloadUtils;
import utils.DriverFactory;
import utils.ExtentManager;

public class UploadAndDownload {
	private static final Logger logger = LoggerFactory.getLogger(UploadAndDownload.class);
	ExtentTest test;
	WebDriver driver;
	UploadAndDownloadPage uploadAndDownloadPage;
	TabsAndDropdownsPage tabsAndDropdownsPage;
	DownloadUtils downloadUtils;
	static {
		System.setProperty("logback.configurationFile", "logback.xml");
	}

	public UploadAndDownload() {
		this.driver = DriverFactory.getDriver();
		uploadAndDownloadPage = new UploadAndDownloadPage(driver);
		tabsAndDropdownsPage = new TabsAndDropdownsPage(driver);
		test = ExtentManager.createTest("Upload and Download Test");
		downloadUtils = new DownloadUtils(System.getProperty("user.home") + "/Downloads");
	}

	@Given("I want to navigate to download and upload page")
	public void i_navigate_to_the_upload_and_download_page() {
		// driver.get(url);
		uploadAndDownloadPage.clickOnElementsDropDown();
		uploadAndDownloadPage.clickOnUploadAndDownloadTab();
		test.info("Navigated to the Upload and Download page: ");
		logger.info("Navigated to the Upload and Download page.");
	}

	@When("I click on the download button")
	public void i_click_on_the_download_button() {
		uploadAndDownloadPage.scrollToDownloadButton();
		downloadUtils.clearDownloadFolder();
		test.info("Cleared the download folder before downloading the file.");
		uploadAndDownloadPage.clickDownloadButton();
		test.info("Clicked on the download button.");
		logger.info("Clicked on the download button to download the file.");

	}

	@Then("I verify the file is downloaded successfully")
	public void verify_the_file_is_downloaded_successfully() {
		// This step is intentionally left blank.
		// The file download verification will be handled in the hooks.
		boolean isDownloaded = downloadUtils.waitForFileToDownload("sampleFile.jpeg", 15);
		Assert.assertTrue(isDownloaded, "File was not downloaded successfully!");
		test.info("File downloaded successfully and verified.");
		logger.info("File downloaded successfully and verified.");

	}

	@When("I upload the file with path {string}")
	public void i_upload_a_file_with_path(String filePath) {
		// uploadAndDownloadPage.scrollToUploadFile();
		uploadAndDownloadPage.scrollToDownloadButton();
		uploadAndDownloadPage.uploadFile(filePath);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.info("Uploaded file with path:" + filePath);
		logger.info("Uploaded file with path: " + filePath);
	}

	@Then("I Verify the uploaded file path is displayed")
	public void verify_the_uploaded_file_path_is_displayed() {
		boolean isDisplayed = uploadAndDownloadPage.uploadedFilepath.isDisplayed();
		Assert.assertTrue(isDisplayed, "uplaoded file path is not displyed!");
		test.info("Uploaded file path is displed successfully: " + uploadAndDownloadPage.uploadedFilepath.getText());
		logger.info("Uploaded file path is displyed successfully: " + uploadAndDownloadPage.uploadedFilepath.getText());
	}

	@Given("I want to click on elements dropdown")
	public void i_want_to_click_on_elements_dropdown() {
		tabsAndDropdownsPage.clickOnElementsDropdown();
		test.info("Clicked on Elements dropdown.");
		logger.info("Clicked on Elements dropdown.");
	}

	@And("I want to select upload and download tab")
	public void i_want_to_select_upload_and_download_tab() {
		tabsAndDropdownsPage.clickOnUploadAndDownloadTab();
		test.info("Selected Upload and Download tab.");
		logger.info("Selected Upload and Download tab.");
	}

}
