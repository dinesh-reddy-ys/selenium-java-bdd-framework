package stepDefs;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DragAndDropPage;
import utils.DriverFactory;

public class DragAndDropStepDefs {
	
	private static final Logger logger = LoggerFactory.getLogger(DragAndDropStepDefs.class);
	static {
		// Ensure consistent logging using the project's logback configuration
		System.setProperty("logback.configurationFile", "logback.xml");
	}
	public WebDriver driver;
	DragAndDropPage dragAndDropPage;
	
	public DragAndDropStepDefs() {
		this.driver = DriverFactory.getDriver();
		dragAndDropPage = new DragAndDropPage(driver);
	}
	
	@When("I perform drag and drop")
	public void i_perform_drag_and_drop() {
		dragAndDropPage.dragAndDrop();
		logger.info("Dragand drop action is performed");
	}
	
	@Then("I verify the text {string} is displayed")
	public void i_verify_the_text_is_displayed(String expectedText) {
		String actualText = dragAndDropPage.getDroppableText();
		if(!actualText.equals(expectedText)) {
			logger.error("Expected text: " + expectedText + ", but got: " + actualText);
			throw new AssertionError("Expected text: " + expectedText + ", but got: " + actualText);
		}
	}
	
	

}
