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
		System.out.println("hello");
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

}
