package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locators for elements on the homepage
    @FindBy(id = "twotabsearchtextbox")
    private WebElement  searchBox ;
    @FindBy(id = "nav-search-submit-button")// Example: Search box
    private WebElement searchButton ;
    @FindBy(linkText ="Login")// Example: Search button
    private WebElement loginLink ; // Example: Login link

    // Methods for actions on the homepage

    /**
     * Navigate to the homepage URL.
     * @param url - The URL of the homepage.
     */
    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    /**
     * Perform a search.
     * @param query - Search query to input.
     */


    /**
     * Click on the login link.
     */


    /**
     * Verify if the search box is displayed on the homepage.
     * @return true if the search box is displayed, otherwise false.
     */


    public void performSearch(String searchData) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(searchData);
        searchButton.click();

    }

}