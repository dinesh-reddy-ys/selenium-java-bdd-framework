package pages;


import interfaces.IAlertsPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;


public class AlertsPage implements IAlertsPage {

    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "alertButton")
    private WebElement alertButton;
    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;
    @FindBy(id = "confirmButton")
    private WebElement confirmButton;
    @FindBy(id = "promptButton")
    private WebElement promptButton;


    // Contructor to initialize page elements and webdriver
    public AlertsPage(WebDriver driver){
     this.driver = driver;
     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @Override
    public void clickOnAlertButton() {
        wait.until(ExpectedConditions.elementToBeClickable(alertButton));
        alertButton.click();
    }

    public void verifyAndAcceptAlert(){
      Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
      Assert.assertNotNull(alert,"Alert is not displayed");
      alert.accept();
    }

    @Override
    public void clickOnConfirmButton() {

    }

    @Override
    public void clickOnTimerAlertButton() {

    }

    @Override
    public void clickOnPromptButton() {

    }
}
