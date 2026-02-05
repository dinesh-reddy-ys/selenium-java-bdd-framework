package stepDefs;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DatePickerPage;
import utils.DriverFactory;


public class DatePickerStepDef {
	private static final Logger logger = LoggerFactory.getLogger(DatePickerStepDef.class);
	static {
		System.setProperty("logback.configuretionFile", "logback.xml");
	}
	ExtentTest test;
	public WebDriver driver;
	public DatePickerPage datePickerPage;
	
	
	public DatePickerStepDef() {
		this.driver = DriverFactory.getDriver();
		datePickerPage = new DatePickerPage(driver);
	}
	
	
	@When("I enter date {string} in date picker input")
	public void i_enter_date_in_the_date_picker(String date) {
		datePickerPage.enterDate(date);	
		logger.info(date + " is enterd in the date picker");
	 }
	@Then("The date picker input should have value {string}")
	public void the_date_picker_input_should_have_value(String expectedDate) {
		String actualDate = datePickerPage.getDate();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		if(!actualDate.equals(expectedDate)) {
			throw new AssertionError("Expected date: " + expectedDate + ", but got: " + actualDate );
		}
		logger.info("Date picker input has the expected value: " + expectedDate);		
	}

}
