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

public class WebTablesPage implements IWebTablesPages {
	public WebDriver driver;
	public WebDriverWait wait;

	@FindBy(id = "addNewRecordButton")
	private WebElement addButton;

	@FindBy(id = "searchBox")
	private WebElement searchBox;

	@FindBy(css = "div.rt-tr-group")
	private List<WebElement> rowLocator;

	@FindBy(css = "div.rt-td")
	private List<WebElement> columnLocator;

	// Table column locator as reusable By object (not hardcoded in method!)
	private final By columnBy = By.cssSelector("div.rt-td");

	public WebTablesPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void clickOnAddButton() {
		addButton.click();
	}

	public void search(String searchText) {
		System.out.println(searchText);
	}

	// Inside method: you can loop through rows and get columns from each row
	public List<List<String>> getWebTableData() {
		List<List<String>> tableData = new ArrayList<>();

		for (WebElement row : rowLocator) {
			// Columns inside each row
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
	 * Verifies whether an employee with given first name and last name
	 * is present in the web table.
	 *
	 * @param firstName employee first name
	 * @param lastName  employee last name
	 * @return true if matching record is found, false otherwise
	 */
	public boolean isEmployeePresentInTable(String firstName,String lastName){
       List<List<String>> tableData = getWebTableData();
	   //Loop through each row
		for(List<String> row : tableData){
           // Saftey check: first name = index 0, last name = index 1
			if(row.size() >= 2){
				String actualFirstName = row.get(0).trim();
				String actualLastName = row.get(1).trim();

				if(actualFirstName.equalsIgnoreCase(firstName) && actualLastName.equalsIgnoreCase(lastName)){
					return true; // match found
				}
			}
		}
		return false; // No match

	}

}
