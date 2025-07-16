package stepDefs;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import utils.DriverFactory;


public class WebTables {

	private static final Logger logger = LoggerFactory.getLogger(WebTables.class);
	ExtentTest test;
	WebDriver driver;


	public WebTables(){
		driver = DriverFactory.getDriver();

	}
}
