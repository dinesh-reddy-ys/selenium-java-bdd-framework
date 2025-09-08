package stepDefs;

import pages.HomePage;
import utils.DriverFactory;
import utils.ExcelUtil;

import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.Map;

public class SearchItem {

    public List<Map<String, String>> searchItem = ExcelUtil.readExcelData("src/test/resources/testData/TestData.xlsx");
    public WebDriver driver;
    public HomePage homePage;
    private List<Map<String, String>> testData;
    private static final String EXCEL_PATH = "src/test/resources/testData/TestData.xlsx";
    static {
        System.setProperty("logback.configurationFile", "logback.xml");
    }

    public SearchItem() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        System.getProperty("username");
        System.getProperty("password");
        loadTestData();
    }
    
    // Method to load test data from Excel
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