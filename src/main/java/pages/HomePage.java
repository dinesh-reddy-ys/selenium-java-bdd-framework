package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IHomePage;

import java.time.Duration;

public class HomePage implements IHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[text() = \"Elements\"]")
    private WebElement elementsCard;
    
    @FindBy(xpath = "//*[text() = \"Forms\"]")
    private WebElement formsCard;	
    @FindBy(xpath = "//*[text() = \"Alerts, Frame & Windows\"]")
    private WebElement alertsFrameWindowsCard;
    @FindBy(xpath = "//*[text() = \"Widgets\"]")
    private WebElement widgetsCard;
    @FindBy(xpath = "//*[text() = \"Interactions\"]")
    private WebElement interactionsCard;
    @FindBy(xpath = "//*[text() = \"Books Store Application\"]")
    private WebElement booksStoreApplicationCard;
    
    
    
    
   
    // Constructor to initialize WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    
    // Actions
    
    public void clickElementsCard() {
    	wait.until(ExpectedConditions.elementToBeClickable(elementsCard));
    	elementsCard.click();
    	
    	
    }
    
    public void clickFormsCard() {
    	wait.until(ExpectedConditions.elementToBeClickable(formsCard));
    	formsCard.click();
    }
    public void clickAlertsFrameWindowsCard() {
    	wait.until(ExpectedConditions.elementToBeClickable(alertsFrameWindowsCard));
    	alertsFrameWindowsCard.click();
    }
    
    public void clickWidgetsCard() {
    	wait.until(ExpectedConditions.elementToBeClickable(widgetsCard));
    	widgetsCard.click();
    }
    public void clickInteractionsCard() {
    	wait.until(ExpectedConditions.elementToBeClickable(interactionsCard));
    	interactionsCard.click();
    }
    public void clickBooksStoreApplicationCard() {
    	wait.until(ExpectedConditions.elementToBeClickable(booksStoreApplicationCard));
    	booksStoreApplicationCard.click();
    }
    
    
    

    
    

}