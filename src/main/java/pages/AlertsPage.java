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
import utils.ScrollUtils;


public class AlertsPage implements IAlertsPage {

    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "alertButton")
    private WebElement alertButton;
    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;
    @FindBy(id = "confirmButton")
    private WebElement confirmButton;
    @FindBy(id = "promtButton")
    private WebElement promptButton;


    // Contructor to initialize page elements and webdriver
    public AlertsPage(WebDriver driver){
     this.driver = driver;
     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void clickOnAlertButton() {
        ScrollUtils.scrollToElement(driver,alertButton);
        wait.until(ExpectedConditions.elementToBeClickable(alertButton));
        alertButton.click();
    }

    public void verifyAndAcceptAlert(){
      Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
      Assert.assertNotNull(alert,"Alert is not displayed");
      alert.accept();
    }

    public void clickOnTimerAlertButton() {
        ScrollUtils.scrollToElement(driver,timerAlertButton);
        wait.until(ExpectedConditions.elementToBeClickable(timerAlertButton));
        timerAlertButton.click();
    }

    public void clickOnConfirmButton() {
        ScrollUtils.scrollToElement(driver,confirmButton);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    public void clickOnPromptButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ScrollUtils.scrollToElement(driver,promptButton);
        wait.until(ExpectedConditions.elementToBeClickable(promptButton));
        promptButton.click();
    }
    public void verifyAndDismissAlert(){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertNotNull(alert,"Alert is not displayed");
        alert.dismiss();
    }
    public void enterPromptText(String text){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertNotNull(alert,"Alert is not displayed");
        alert.sendKeys(text);
    }
}
