package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import pages.WebTablesPage;
import utils.DriverFactory;
import utils.ExtentManager;

import java.util.List;


public class WebTables {

	private static final Logger logger = LoggerFactory.getLogger(WebTables.class);
	ExtentTest test;
	WebDriver driver;
    WebTablesPage webTablesPage;

	public WebTables(){
		driver = DriverFactory.getDriver();
        webTablesPage = new WebTablesPage(driver);
        test = ExtentManager.createTest("Web table test");
	}

	@When("I read data from web table")
	public void i_read_data_from_web_table(){
		List<List<String>> tableData = webTablesPage.getWebTableData();

		for(List<String> row : tableData){
			for(String cell : row){
				System.out.print(cell + " | ");

			}
		}
	}
	
	@Given("I am on the web table page")
	public void i_am_on_the_web_table_page() {
		// navigate to the web page
		driver.get("https://demoqa.com/webtables");
		test.info("navigated to the web table page");
		logger.info("navigated to the web table page");
	}
	
	@When("I load the table data")
	public void i_load_the_table_data() {
		// Ensure the table data is loaded
		//Assert.assertTrue(webTablesPage);
		test.info("Loading web table data...");
		logger.info("Web table data is loaded.");
		
	}
	
	@Then("I should see {string} in the web table")
	public void i_should_see_value_in_the_web_table(String value) {
	  // Verify the value exists in the web table
		List<List<String>> tableData = webTablesPage.getWebTableData();
		boolean valueFound = tableData.stream()
				.flatMap(List::stream)
				.anyMatch(cell -> cell.equalsIgnoreCase(value));
		
		Assert.assertTrue(valueFound, "Value " + value + " not found in the web table.");
		test.info("Verified that value '" + value + "' exists in the web table.");
		logger.info("Verified that value '{}' exists in tghe web table.",value);
	}
	
	
}
