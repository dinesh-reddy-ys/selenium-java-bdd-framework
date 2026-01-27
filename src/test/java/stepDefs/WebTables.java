package stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import pages.RegistrationFormPage;
import pages.WebTablesPage;
import utils.DriverFactory;

import java.util.List;
import java.util.Map;


public class WebTables {

	private static final Logger logger = LoggerFactory.getLogger(WebTables.class);
	ExtentTest test;
	WebDriver driver;
    WebTablesPage webTablesPage;
	RegistrationFormPage registrationFormPage;
	private Map<String,String> employeeData;

	public WebTables(){
		driver = DriverFactory.getDriver();
        webTablesPage = new WebTablesPage(driver);
		registrationFormPage = new RegistrationFormPage(driver);
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
	@When("the user clicks on the add button")
	public void the_user_clicks_on_the_add_button(){
     webTablesPage.clickOnAddButton();
	}
	@And("the user enters valid employee details")
	public void the_user_enters_valid_details(DataTable dataTable){
		// Convert DataTable to Map (key-Value)
		employeeData = dataTable.asMaps(String.class,String.class).get(0);
        registrationFormPage.enterFirstName(employeeData.get("firstName"));
		registrationFormPage.enterLastName(employeeData.get("lastName"));
		registrationFormPage.enterEmail(employeeData.get("email"));
		registrationFormPage.enterAge(employeeData.get("age"));
		registrationFormPage.enterSalary(employeeData.get("salary"));
		registrationFormPage.enterDepartment(employeeData.get("department"));
	}
	@And("user submits the form")
	public void user_submits_the_form(){
      registrationFormPage.clickOnSubmitButton();
	}
	@Then("the new employee record should be displayed in the table")
	public void the_new_employee_record_should_be_displayed_in_the_table(DataTable dataTable){
      // Conv ert data tabkle to Map
		Map<String,String> emoployeeData = dataTable.asMaps(String.class, String.class).get(0);

		String firstName = employeeData.get("firstName");
		String lastName = employeeData.get("lastName");

		boolean isPresent = webTablesPage.isEmployeePresentInTable(firstName,lastName);

		Assert.assertTrue(isPresent,"Employee not found in table: " + firstName + " " + lastName);
	}





}
