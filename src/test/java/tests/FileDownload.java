package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.UploadAndDownloadPage;
import utils.DownloadUtils;

public class FileDownload {
	
	@Test
	public void testFileDownload() {
		DownloadUtils downloadUtils = new DownloadUtils(System.getProperty("user.home") + "/Downloads");
		// Initialize WebDriver and navigate to the page
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/upload-download");
		driver.manage().window().maximize();
		
		// Create an instance of UploadAndDownloadPage
		UploadAndDownloadPage uploadAndDownloadPage = new UploadAndDownloadPage(driver);
		downloadUtils.clearDownloadFolder();
		// Scroll to the download button
		uploadAndDownloadPage.scrollToDownloadButton();
		
		// Click the download button
		uploadAndDownloadPage.clickDownloadButton();
		
		// Verify file download (this part may require additional logic to check the downloaded file)
		
		
		boolean isDownloaded = downloadUtils.waitForFileToDownload("sampleFile.jpeg", 30);
		
		Assert.assertTrue(isDownloaded, "File was not downloaded successfully!");
		driver.quit();
		
	}
	@Test
	public void testFileExists() {
		DownloadUtils downloadUtils = new DownloadUtils(System.getProperty("user.home") + "/Downloads");
		boolean  deleted =downloadUtils.deleteSpecificFile("sampleFile.jpeg");
		Assert.assertTrue(deleted, "File was not deleted successfully!");
	}
	
	@Test
	public void testFileUpload() {
		
	}

}
