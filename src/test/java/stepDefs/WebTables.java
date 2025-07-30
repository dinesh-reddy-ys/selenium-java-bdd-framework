package stepDefs;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import pages.WebTablesPage;
import utils.DriverFactory;

import java.util.List;


public class WebTables {

	private static final Logger logger = LoggerFactory.getLogger(WebTables.class);
	ExtentTest test;
	WebDriver driver;
    WebTablesPage webTablesPage;

	public WebTables(){
		driver = DriverFactory.getDriver();
        webTablesPage = new WebTablesPage(driver);
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
}
