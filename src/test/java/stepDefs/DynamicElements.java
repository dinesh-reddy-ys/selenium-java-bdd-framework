package stepDefs;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import pages.DynamicPage;
import utils.DriverFactory;
import utils.ExtentManager;

public class DynamicElements {
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicElements.class);
	ExtentTest test;
	public WebDriver driver;
	public DynamicPage dynamicPage;
	static {
		System.setProperty("logback.configurationFile", "logback.xml");
	}
	
	public DynamicElements() {
		this.driver = DriverFactory.getDriver();
		 dynamicPage = new DynamicPage(driver);
		 test = ExtentManager.createTest("Dynamic Elements Test");
	}
	
	@Given("I navigate to the dynamic page with URL {string}")
    public void i_navigate_to_the_dynamic_page_with_URL(String url) {
		DynamicPage dynamicPage = new DynamicPage(driver);
		dynamicPage.navigateToDynamicPage(url);
		test.info("Navigated to the dynamic page with URL: " + url);
		logger.info("Navigated to the dynamic page with URL: " + url);
	}
	
	@Given("I get the text from the dynamic element")
	public void i_get_the_text_from_the_dynamic_element() {
		dynamicPage.scrollToDynamicElement();
		String dynamicText = dynamicPage.getDynamicElementText();
		System.out.println("Dynamic Element Text: " + dynamicText);
		test.info("Retrieved text from the dynamic element: " + dynamicText);
		logger.info("Retrieved text from the dynamic element: " + dynamicText);
	}
}
