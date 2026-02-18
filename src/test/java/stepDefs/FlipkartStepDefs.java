package stepDefs;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FlipkartPage;
import utils.DriverFactory;

public class FlipkartStepDefs {
	
	public FlipkartPage flipkartPage;
	WebDriver driver;
	public FlipkartStepDefs() {
		this.driver = DriverFactory.getDriver();
		flipkartPage = new FlipkartPage(driver);
	}
	
	
	
	
	@When("I search for {string}")
	public void i_search_for(String productName) {
		flipkartPage.searchForProduct(productName);
	}
	
	@Then("I verify that all search results contains {string}")
	public void i_verify_That_all_results_contains(String productName) {
		Assert.assertTrue(flipkartPage.areAllTitlesContaining(productName), "Not all product titles contain: " + productName);
	}
	

}
