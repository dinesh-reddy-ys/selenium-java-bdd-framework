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

/**
 * Cucumber step definitions for scenarios that interact with the Web Tables
 * demo page.
 *
 * <p>
 * These step definitions are intentionally thin: they delegate browser
 * interactions to the {@link WebTablesPage} and {@link RegistrationFormPage}
 * page objects. Keeping the glue code minimal helps keep feature files readable
 * and maintains a clear separation between test intent (steps) and Selenium
 * mechanics (page objects).
 * </p>
 */
public class WebTablesStepDefs {

	// Logger for step-level messages and debugging output
	private static final Logger logger = LoggerFactory.getLogger(WebTablesStepDefs.class);

	static {
		// Ensure consistent logging using the project's logback configuration
		System.setProperty("logback.configurationFile", "logback.xml");
	}

	// Optional Extent report test object used by some test runners
	ExtentTest test;

	// Shared WebDriver instance provided by the DriverFactory
	WebDriver driver;

	// Page objects used by these steps
	WebTablesPage webTablesPage;
	RegistrationFormPage registrationFormPage;

	// Temporary holder for employee data supplied from Cucumber DataTable
	private Map<String, String> employeeData;

	/**
	 * Initialize shared resources (driver and page objects) used across steps.
	 * DriverFactory.getDriver() should return a configured WebDriver instance
	 * created by the test runner (so steps can remain independent of driver
	 * creation details).
	 */
	public WebTablesStepDefs() {
		driver = DriverFactory.getDriver();
		webTablesPage = new WebTablesPage(driver);
		registrationFormPage = new RegistrationFormPage(driver);
	}

	/**
	 * Step: Read and print the visible web table data.
	 *
	 * This step demonstrates how to extract an in-memory representation of the
	 * table and iterate over its rows and cells. The actual extraction logic lives
	 * in {@link WebTablesPage#getWebTableData()}.
	 */
	@When("I read data from web table")
	public void i_read_data_from_web_table() {
		List<List<String>> tableData = webTablesPage.getWebTableData();

		// Print table contents to stdout: row by row, cell by cell
		for (List<String> row : tableData) {
			for (String cell : row) {
				System.out.print(cell + " | ");

			}
		}
	}

	/**
	 * Step: Click the Add button on the Web Tables page which opens the
	 * registration form for adding a new employee.
	 */
	@When("the user clicks on the add button")
	public void the_user_clicks_on_the_add_button() {
		webTablesPage.clickOnAddButton();
	}

	/**
	 * Step: Fill the registration form with valid employee details supplied via a
	 * Cucumber DataTable. The DataTable is converted to a Map so keys match the
	 * form field names.
	 *
	 * @param dataTable Cucumber DataTable containing a single row of employee data
	 */
	@And("the user enters valid employee details")
	public void the_user_enters_valid_details(DataTable dataTable) {
		// Convert the DataTable to a single Map (first row) for easy access
		employeeData = dataTable.asMaps(String.class, String.class).get(0);

		// Delegate to the RegistrationFormPage to set each input field
		registrationFormPage.enterFirstName(employeeData.get("firstName"));
		registrationFormPage.enterLastName(employeeData.get("lastName"));
		registrationFormPage.enterEmail(employeeData.get("email"));
		registrationFormPage.enterAge(employeeData.get("age"));
		registrationFormPage.enterSalary(employeeData.get("salary"));
		registrationFormPage.enterDepartment(employeeData.get("department"));
	}

	/**
	 * Step: Submit the registration form. The page object handles clicking the
	 * submit button and any waits required for the operation to complete.
	 */
	@And("user submits the form")
	public void user_submits_the_form() {
		registrationFormPage.clickOnSubmitButton();
	}

	/**
	 * Step: Verify that the newly added employee record is present in the web
	 * table.
	 *
	 * The DataTable passed into this step represents the expected data. The
	 * implementation below uses the firstName and lastName captured earlier from
	 * the form DataTable to assert presence. This keeps verification focused and
	 * resilient to extra or reordered columns in the table.
	 *
	 * @param dataTable DataTable describing the expected employee record
	 */
	@Then("the new employee record should be displayed in the table")
	public void the_new_employee_record_should_be_displayed_in_the_table(DataTable dataTable) {
		// Convert data table for clarity (not used directly in the current
		// assertion but available for future richer checks)
		Map<String, String> emoployeeData = dataTable.asMaps(String.class, String.class).get(0);

		// Use the first/last name that was stored when filling the form
		String firstName = employeeData.get("firstName");
		String lastName = employeeData.get("lastName");

		// Delegate to the page object which inspects the table DOM
		boolean isPresent = webTablesPage.isEmployeePresentInTable(firstName, lastName);

		// Assert presence and provide a helpful failure message if missing
		Assert.assertTrue(isPresent, "Employee not found in table: " + firstName + " " + lastName);
	}

}