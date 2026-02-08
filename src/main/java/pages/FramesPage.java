package pages;

import interfaces.IFramesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object representing the Frames demo page.
 *
 * <p>This class encapsulates interactions with an iframe on the page. It provides
 * methods to switch into a frame and to retrieve text from an element inside the frame.
 * The class uses PageFactory to create WebElement instances for cleaner tests.</p>
 */
public class FramesPage implements IFramesPage {

	// WebDriver used for browser interactions
	private WebDriver driver;

	// Explicit wait helper (initialized in constructor). Useful if more waits are added.
	private WebDriverWait wait;

	// Locator for the frame element. Using @FindBy allows PageFactory to initialize it.
	@FindBy(id = "frame1")
	private WebElement frame1;

	// Element inside the frame containing the sample heading text
	@FindBy(id = "sampleHeading")
	private WebElement frameText;

	/**
	 * Constructor - initializes WebDriver, explicit wait and PageFactory elements.
	 *
	 * @param driver the WebDriver instance to use for interactions
	 */
	public FramesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Switches the WebDriver context into the iframe represented by {@code frame1}.
	 *
	 * <p>After calling this method, further calls to find elements or interact will
	 * operate within the iframe until the driver switches back to the default content.</p>
	 */
	public void switchToFrameById() {
		// Use the WebElement reference to switch to the frame; this is robust and
		// avoids relying on string IDs which may change.
		driver.switchTo().frame(frame1);
	}

	/**
	 * Returns the visible text of the heading element inside the iframe.
	 *
	 * @return the text content of the heading inside the frame
	 */
	public String getFrameText() {
		// Note: caller should ensure driver is already switched into the frame
		// before calling this method; otherwise NoSuchElementException may occur.
		return frameText.getText();
	}
}