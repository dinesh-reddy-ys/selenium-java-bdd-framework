package stepDefs;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AlertsPage;
import utils.DriverFactory;

public class Alerts {
    private static  final Logger logger = LoggerFactory.getLogger(Alerts.class);
	ExtentTest test;
	public WebDriver driver;
	public AlertsPage alertsPage;
	static {
		System.setProperty("logback.configurationFile","logback.xml");
	}

   public Alerts(){
		driver = DriverFactory.getDriver();
		alertsPage = new AlertsPage(driver);

   }

	@When("Click on the alert button")
	public void iClickOnTheAlertButton() {
     alertsPage.clickOnAlertButton();

	}
	
	@Then("Accept the alert")
	public void iShouldSeeAnAlertWithTheMessage() {
     alertsPage.verifyAndAcceptAlert();
	}

}
