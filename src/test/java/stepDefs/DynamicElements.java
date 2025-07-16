package stepDefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import pages.DynamicPage;
import utils.DriverFactory;

public class DynamicElements {
	public WebDriver driver;
	public DynamicPage dynamicPage;
	
	public DynamicElements() {
		this.driver = DriverFactory.getDriver();
		 dynamicPage = new DynamicPage(driver);
	}
	
	@Given("I navigate to the dynamic page with URL {string}")
    public void i_navigate_to_the_dynamic_page_with_URL(String url) {
		DynamicPage dynamicPage = new DynamicPage(driver);
		dynamicPage.navigateToDynamicPage(url);
	}
	
	@Given("I get the text from the dynamic element")
	public void i_get_the_text_from_the_dynamic_element() {
		dynamicPage.scrollToDynamicElement();
		String dynamicText = dynamicPage.getDynamicElementText();
		System.out.println("Dynamic Element Text: " + dynamicText);
	}
}
