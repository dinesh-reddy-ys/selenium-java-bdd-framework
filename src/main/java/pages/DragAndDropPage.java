package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IDragAndDropPage;
import utils.BaseUtils;

/**
 * Page Object representing a page that contains a draggable element and a droppable target.
 *
 * <p>This class encapsulates the interactions required to perform a drag-and-drop
 * operation using Selenium WebDriver's Actions API and provides a helper to read
 * the resulting text after a successful drop.</p>
 *
 * <p>Usage example:
 *   DragAndDropPage page = new DragAndDropPage(driver);
 *   page.dragAndDrop();
 *   assertEquals("Dropped!", page.getDroppableText());
 * </p>
 */
public class DragAndDropPage implements IDragAndDropPage {
    
    // WebDriver instance used to interact with the browser
    WebDriver driver;
    
    // Explicit wait used to wait for elements to be visible/interactable
    WebDriverWait wait;
    
    // The element that will be dragged. Located by id 'draggable'
    @FindBy(xpath = "//div[@id='draggable']")
    private WebElement draggable;
    
    // The droppable container; we locate the parent div that contains the 'Drop Here' text
    @FindBy(xpath = "//div[@id='droppable']//p[text()='Drop Here']/parent::div")
    private WebElement droppable;
    
    // The text element that appears inside the droppable container after a successful drop
    @FindBy(xpath = "//div[@id='droppable']//p[text()='Dropped!']")
    private WebElement droppedText;
    
    /**
     * Constructor.
     *
     * @param driver WebDriver instance to use for interactions. The constructor
     *               initializes the PageFactory fields and an explicit wait.
     */
    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        // Use a 10-second explicit wait for element visibility checks
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Performs a drag-and-drop operation from the draggable element to the droppable target.
     *
     * The method waits for both elements to be visible, then uses the Actions API to:
     * - move to the source element
     * - click and hold it
     * - optionally pause to more closely emulate a human drag
     * - move to the target element
     * - release the mouse to drop
     *
     * Note: Depending on the application under test and the browser/driver, the
     * direct Actions.dragAndDrop(source, target) call may not work reliably, so
     * this method uses a low-level sequence of actions with small pauses.
     */
    public void dragAndDrop() {
        // Wait until both source and target are visible before interacting
        wait.until(ExpectedConditions.elementToBeClickable(draggable));
        wait.until(ExpectedConditions.visibilityOf(droppable));
        ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", draggable);
        // Perform a more robust drag-and-drop sequence instead of the single dragAndDrop call
        BaseUtils.dragAndDrop(draggable, droppable, driver);
    }
    
    /**
     * Returns the text displayed in the droppable area after a successful drop.
     *
     * @return the displayed droppable text (for example, "Dropped!")
     */
    public String getDroppableText() {
        return droppedText.getText();
    }
    
}