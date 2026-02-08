package pages;

import interfaces.IWebTablesPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.*;

/**
 * Page object representing the "Web Tables" page used in the test suite.
 *
 * <p>This class exposes interactions and helpers for the web table on the page
 * such as clicking the Add button, searching (placeholder) and extracting the
 * table's data as a nested list of strings. The implementation uses Selenium's
 * PageFactory annotations to locate elements and provides a small utility to
 * verify whether a particular employee record exists in the table.</p>
 *
 * <p>Design notes:
 * - The page keeps a reusable By locator for columns so column lookups inside
 *   each row are not hardcoded repeatedly.
 * - Methods return plain Java collections (List<List<String>>) to keep the
 *   page object framework-agnostic and easy to assert in tests.
 * </p>
 */
public class WebTablesPage implements IWebTablesPages {
	public WebDriver driver; // WebDriver instance supplied by tests
	public WebDriverWait wait; // Explicit wait instance for future use

	// 'Add' button that opens the record form
	@FindBy(id = "addNewRecordButton")
	private WebElement addButton;

	// Input used to filter/search the displayed table rows
	@FindBy(id = "searchBox")
	private WebElement searchBox;

	// Each row in the React table is represented by this locator
	@FindBy(css = "div.rt-tr-group")
	private List<WebElement> rowLocator;

	// A generic locator for any table cell (used when needed)
	@FindBy(css = "div.rt-td")
	private List<WebElement> columnLocator;

	// Table column locator as reusable By object (not hardcoded in method!)
	private final By columnBy = By.cssSelector("div.rt-td");

	/**
	 * Construct the page object and initialize WebElements via PageFactory.
	 *
	 * @param driver the WebDriver instance to drive the browser
	 */
	public WebTablesPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks the "Add" button to open the new record form.
	 * The method intentionally keeps behavior simple — it only performs the click.
	 */
	public void clickOnAddButton() {
		addButton.click();
	}

	/**
	 * Placeholder for searching the table via the search box.
	 * Currently prints the provided text to stdout; consider wiring this to
	 * actually type into the search box (searchBox.sendKeys(...)) if tests
	 * require live filtering.
	 *
	 * @param searchText text to search/filter the table by
	 */
	public void search(String searchText) {
		System.out.println(searchText);
	}

	// Inside method: you can loop through rows and get columns from each row
	/**
	 * Extracts the visible web table data into a nested List structure.
	 *
	 * @return List of rows where each row is a List of String cell values
	 */
	public List<List<String>> getWebTableData() {
		List<List<String>> tableData = new ArrayList<>();

		for (WebElement row : rowLocator) {
			// Columns inside each row — find using reusable By locator to scope
			// the search to the current row (avoids using the page-level
			// columnLocator which returns every column on the page).
			List<WebElement> columns = row.findElements(columnBy);
			List<String> rowData = new ArrayList<>();

			for (WebElement column : columns) {
				rowData.add(column.getText());
			}

			tableData.add(rowData);
		}

		return tableData;
	}

	/**
	 * Verifies whether an employee with given first name and last name is present
	 * in the web table.
	 *
	 * @param firstName employee first name
	 * @param lastName  employee last name
	 * @return true if matching record is found, false otherwise
	 */
	public boolean isEmployeePresentInTable(String firstName, String lastName) {
		List<List<String>> tableData = getWebTableData();
		// Loop through each row
		for (List<String> row : tableData) {
			// Saftey check: first name = index 0, last name = index 1
			if (row.size() >= 2) {
				String actualFirstName = row.get(0).trim();
				String actualLastName = row.get(1).trim();

				if (actualFirstName.equalsIgnoreCase(firstName) && actualLastName.equalsIgnoreCase(lastName)) {
					return true; // match found
				}
			}
		}
		return false; // No match

	}

}