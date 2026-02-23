package stepDefs;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
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
	
	@Then("I select items with prices less than {int}")
	public void i_select_items_with_prices_less_than(Integer price) {
		flipkartPage.selectItemsWithPricesLessThan(price);
	}
	
	@And("I click on the next page button")
	public void i_click_on_next_page_button() {
		flipkartPage.clickNextPage();
	}
	
	@And("add first item to the cart")
	public void add_first_item_to_cart() {
		flipkartPage.clickAddToCartForFirstProduct();
	}
	
	@Then("delete the item from the cart in the same page")
	public void delete_item_from_cart() {
		flipkartPage.clickDeleteButton();
	}
	

}
