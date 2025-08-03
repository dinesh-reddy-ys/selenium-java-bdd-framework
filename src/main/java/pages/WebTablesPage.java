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
    // WebDriver instance to interact with the browser
    public WebDriver driver;

    // WebDriverWait instance for explicit waits
    public WebDriverWait wait;

    // WebElement for the "Add" button
    @FindBy(id = "addNewRecordButton")
    private WebElement addButton;

    // WebElement for the search box
    @FindBy(id = "searchBox")
    private WebElement searchBox;

    // List of WebElements representing rows in the table
    @FindBy(css = "div.rt-tr-group")
    private List<WebElement> rowLocator;

    // List of WebElements representing columns in the table
    @FindBy(css = "div.rt-td")
    private List<WebElement> columnLocator;

    // Locator for table columns as a reusable By object
    private final By columnBy = By.cssSelector("div.rt-td");

    // Constructor to initialize the WebTablesPage with WebDriver and WebDriverWait
    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Method to click on the "Add" button
    public void clickOnAddButton() {
        System.out.println("hello");
    }

    // Method to perform a search in the search box
    public void search(String searchText) {
        System.out.println(searchText);
    }

    // Method to retrieve all data from the web table
    // Returns a list of rows, where each row is a list of column values
    public List<List<String>> getWebTableData() {
        List<List<String>> tableData = new ArrayList<>();

        // Loop through each row in the table
        for (WebElement row : rowLocator) {
            // Find all columns within the current row
            List<WebElement> columns = row.findElements(columnBy);
            List<String> rowData = new ArrayList<>();

            // Extract text from each column and add it to the row data
            for (WebElement column : columns) {
                rowData.add(column.getText());
            }

            // Add the row data to the table data
            tableData.add(rowData);
        }

        return tableData;
    }
}
