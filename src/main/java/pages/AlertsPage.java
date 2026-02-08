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

/**
 * Page object representing the Alerts demo page.
 *
 * <p>This class centralizes interactions with JavaScript alerts on the page. It uses
 * a {@link WebDriverWait} to wait for elements and alerts, and a small ScrollUtils
 * helper to ensure elements are visible before actions.</p>
 *
 * <p>All methods in this class perform assertions (via TestNG's {@link Assert}) where
 * an alert must be present before performing alert-specific operations. The class
 * intentionally does not change any test flow behavior — only documents and clarifies
 * the existing logic with Javadoc and inline comments.</p>
 */
public class AlertsPage implements IAlertsPage {

    // WebDriver instance used for page interactions
    WebDriver driver;

    // Explicit wait used to wait for elements and alerts
    WebDriverWait wait;

    // Button that triggers a simple alert (immediate)
    @FindBy(id = "alertButton")
    private WebElement alertButton;

    // Button that triggers an alert after a delay
    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;

    // Button that triggers a confirm dialog (accept/dismiss)
    @FindBy(id = "confirmButton")
    private WebElement confirmButton;

    // Button that triggers a prompt dialog (allows input)
    @FindBy(id = "promtButton")
    private WebElement promptButton;

    /**
     * Constructor - initializes page elements and the explicit wait.
     *
     * @param driver the WebDriver instance that will be used by this page object
     */
    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        // Use a 10 second explicit wait for element/alert conditions
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Scrolls the page to the alert button and clicks it to trigger an immediate alert.
     */
    public void clickOnAlertButton() {
        // Ensure the button is visible (some examples render it below the fold)
        ScrollUtils.scrollToElement(driver, alertButton);
        // Wait until the element is clickable to avoid stale/overlay issues
        wait.until(ExpectedConditions.elementToBeClickable(alertButton));
        alertButton.click();
    }

    /**
     * Waits for an alert to be present, verifies it's displayed, and accepts it.
     *
     * This method asserts the alert exists to fail fast if the alert did not appear.
     */
    public void verifyAndAcceptAlert() {
        // Wait for the JS alert to be present and switch to it
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        // Fail the test with a clear message if the alert is not displayed
        Assert.assertNotNull(alert, "Alert is not displayed");
        // Accept the alert (click "OK")
        alert.accept();
    }

    /**
     * Scrolls to and clicks the button that triggers an alert after a short timer.
     */
    public void clickOnTimerAlertButton() {
        ScrollUtils.scrollToElement(driver, timerAlertButton);
        wait.until(ExpectedConditions.elementToBeClickable(timerAlertButton));
        timerAlertButton.click();
    }

    /**
     * Scrolls to and clicks the confirm button which shows a confirm dialog.
     */
    public void clickOnConfirmButton() {
        ScrollUtils.scrollToElement(driver, confirmButton);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    /**
     * Scrolls to and clicks the prompt button which opens a prompt alert that
     * accepts text input. An implicit wait is set briefly here in the original
     * implementation prior to clicking — we keep it to avoid altering timing.
     */
    public void clickOnPromptButton() {
        // Preserve the original implicit wait call; this complements the explicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ScrollUtils.scrollToElement(driver, promptButton);
        wait.until(ExpectedConditions.elementToBeClickable(promptButton));
        promptButton.click();
    }

    /**
     * Waits for an alert and dismisses it (clicks "Cancel" or closes the alert).
     */
    public void verifyAndDismissAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertNotNull(alert, "Alert is not displayed");
        alert.dismiss();
    }

    /**
     * Waits for a prompt alert to be present, asserts it exists, and sends the provided text.
     *
     * @param text the text to enter into the prompt alert
     */
    public void enterPromptText(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertNotNull(alert, "Alert is not displayed");
        // Send text to the prompt input field
        alert.sendKeys(text);
    }
}