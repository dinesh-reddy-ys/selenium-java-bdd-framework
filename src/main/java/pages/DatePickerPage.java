package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object for the Date Picker component on the demo site.
 *
 * <p>This class wraps interactions with the date input field. It uses an explicit
 * {@link WebDriverWait} to wait for visibility and relies on a small JavaScript
 * snippet to clear the field before sending new input. The implementation keeps
 * interactions simple (enter and read) and avoids making assumptions about the
 * date picker's internal DOM beyond the visible input.</p>
 */
public class DatePickerPage {
	WebDriver driver;
	WebDriverWait wait;

	// The visible input field used by the date picker. We interact with this element
	// directly by clearing its value and sending keystrokes so tests remain stable
	// even if the calendar widget is rendered as a separate overlay.
	@FindBy(id = "datePickerMonthYearInput")
	private WebElement dateInputField;

	/**
	 * Constructor - initializes page elements and an explicit wait helper.
	 *
	 * @param driver the WebDriver instance to use for interactions
	 */
	public DatePickerPage(WebDriver driver) {
		this.driver = driver;
		// 10 second explicit wait to reduce flakiness when waiting for element
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Enters the provided date string into the date input field.
	 *
	 * <p>The method clears the field using JavaScript to avoid side-effects from
	 * the browser's native clear behaviour, sends the date characters, then
	 * presses ENTER to ensure the date is applied. A small pause is avoided but
	 * the sendKeys interaction matches how users type into the control.</p>
	 *
	 * @param date the date string to enter (format depends on the application)
	 */
	public void enterDate(String date) {
		// Ensure the input is visible before interacting
		wait.until(ExpectedConditions.visibilityOf(dateInputField));
		// Focus the field so that JS clearing and keystrokes affect it
		dateInputField.click();
		// Clear the input value using JS to avoid issues with React-controlled inputs
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='';", dateInputField);
		// Send the desired date string as keystrokes; this helps React detect changes
		// and replicates user behavior better than setting the value via JS.
		dateInputField.sendKeys(date);
		// Press ENTER to apply the date selection (some date pickers require this)
		dateInputField.sendKeys(Keys.ENTER);
		// Lightweight log for test debugging — retained from original implementation
		System.out.println("Date entered: " + date);
	}

	/**
	 * Reads and returns the current value of the date input field.
	 *
	 * @return the current value of the date input, or an empty string if not present
	 */
	public String getDate() {
		wait.until(ExpectedConditions.visibilityOf(dateInputField));
		return dateInputField.getAttribute("value");
	}

}