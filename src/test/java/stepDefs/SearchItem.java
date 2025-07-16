package stepDefs;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.DriverFactory;
import utils.ExcelUtil;

import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import java.util.List;
import java.util.Map;

public class SearchItem {

    private static final Logger logger = LoggerFactory.getLogger(SearchItem.class);
    public List<Map<String, String>> searchItem = ExcelUtil.readExcelData("src/test/resources/testData/TestData.xlsx");
    public WebDriver driver;
    public HomePage homePage;
    private List<Map<String, String>> testData;
    private static final String EXCEL_PATH = "src/test/resources/testData/TestData.xlsx";
    private String username;
    private String password;
    static {
        System.setProperty("logback.configurationFile", "logback.xml");
    }

    public SearchItem() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        this.username = System.getProperty("username");
        this.password = System.getProperty("password");
        // Load test data from Excel
        loadTestData();
    }

    private void loadTestData() {
        try {
            testData = ExcelUtil.readExcelData(EXCEL_PATH);
            if (testData.isEmpty()) {
                throw new RuntimeException("No data found in Excel file: " + EXCEL_PATH);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data from Excel: " + e.getMessage());
        }
    }

    
}