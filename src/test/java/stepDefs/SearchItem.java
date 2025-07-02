package stepDefs;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import pages.HomePage;
import utils.DriverFactory;
import utils.ExcelUtil;

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

    @Given("I launch the url")
    public void i_launch_the_url() {
        //System.out.println("Launching the url...");
    	
        logger.info("Launching the url..");

        homePage.navigateToHomePage("https://www.amazon.in/");

    }
    @When("I search for a item")
    public void i_search_for_a_item() {


//        try {
//            // Get the search term from Excel data
//            String searchTerm = testData.get(0).get("SearchItem");
//            System.out.println("Search term: " + searchTerm);
//            if (searchTerm == null || searchTerm.trim().isEmpty()) {
//                throw new RuntimeException("Search term is empty in Excel data");
//            }
//
//            System.out.println("Searching for item: " + searchTerm);
//            homePage.performSearch(searchTerm);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to perform search: " + e.getMessage());
//        }
//        homePage.performSearch("mobile");
        System.out.println("Searching for item: " );

    }
    @Then("select the item from search list")
    public void select_the_item_from_search_list() {
//        searchResult = homePage.getSearchResult();
//        searchResultLink = homePage.getSearchResultLink();
        System.out.println("Search result: " );
        System.out.println("Search result link: " );
    }
    @Given("search all items")
    public void search_all_items() {
        String filePath = "src/test/resources/testData/TestData.xlsx";
        List<String> searchItems = ExcelUtil.getSearchItems(filePath, "SearchItems");

        for(String item : searchItems) {
            System.out.println("Searching for item: " + item);
            homePage.performSearch(item);
        }
    }

    @Given("user logs in with valid username {string} and password {string}")
    public  void user_logs_in(String placeholderUser,String placeholderPass){
        homePage.navigateToHomePage("https://www.amazon.in/");
        List<Map<String,String>> data = ExcelUtil.readExcelData("src/test/resources/testData/TestData.xlsx");
        for (Map<String, String> row : data) {
            String username = row.get("username");
            String password = row.get("password");
            System.out.println("Login attempt: " + username + " / " + password);
            // call your actual login logic here
            homePage.performSearch(username);
        }
    }

    @Then("user should see the dashboard")
    public void seedashboard(){
        System.out.println("see dashboard");
    }
}