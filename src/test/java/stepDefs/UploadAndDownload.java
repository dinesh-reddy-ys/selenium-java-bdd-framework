package stepDefs;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.UploadAndDownloadPage;
import utils.DriverFactory;
import utils.ExtentManager;

public class UploadAndDownload {
	
	ExtentTest test;
	WebDriver driver;
	UploadAndDownloadPage uploadAndDownloadPage;
	
	public UploadAndDownload() {
		this.driver = DriverFactory.getDriver();
		uploadAndDownloadPage = new UploadAndDownloadPage(driver);
		test = ExtentManager.createTest("Upload and Download Test");
		
	}
	
	@Given("I want to navigate to download and upload page {string}")
	public void i_navigate_to_the_upload_and_download_page(String url) {
		driver.get(url);
		test.info("Navigated to the Upload and Download page: " + url);
	}
	@When("I click on the download button")
	public void i_click_on_the_download_button() {
		uploadAndDownloadPage.scrollToDownloadButton();
		uploadAndDownloadPage.clickDownloadButton();
		test.info("Clicked on the download button.");
	}
	@Then("verify the file is downloaded successfully")
	public void verify_the_file_is_downloaded_successfully() {
		// This step is intentionally left blank.
		// The file download verification will be handled in the hooks.
		
		System.out.println("File download verification will be handled in the hooks.");
	}

}
