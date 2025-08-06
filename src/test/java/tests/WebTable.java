package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;

public class WebTable {
	
	@Test
	public void testWebTable() {
		ExtentReports extent = ExtentManager.getInstance();
		// Create a new instance of the extent report
		ExtentTest test = extent.createTest("Web Table Test");
		
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/webtables");
		driver.manage().window().maximize();
		// Interacting with the web table.
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> rows = driver.findElements(By.cssSelector("div.rt-tr-group"));
		for(WebElement element : rows) {
			List< WebElement> columns = element.findElements(By.cssSelector("div.rt-td"));
			for(WebElement column : columns) {
				String text = column.getText();
				System.out.print(text + " | ");
				test.info(text);
			}
			
		}
		extent.flush();
		
		driver.quit();		
	}

}
