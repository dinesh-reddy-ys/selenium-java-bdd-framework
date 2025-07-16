package stepDefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pages.HomePage;
import utils.DriverFactory;

public class Home {
	
	WebDriver driver;
	HomePage homePage;
	
	public Home() {
		this.driver = DriverFactory.getDriver();
		homePage = new HomePage(driver);
	}
	
	
	@And("I click on books store application card")
	public void i_click_on_books_store_application_card() {
		homePage.clickBooksStoreApplicationCard();
	}
	@And("I click on elements card")
	public void i_click_on_elements_card() {
		homePage.clickElementsCard();
	}

}
