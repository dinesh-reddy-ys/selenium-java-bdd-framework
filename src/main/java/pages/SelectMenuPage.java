package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Page object representing the "Select Menu" page used in tests.
 *
 * <p>
 * This class provides helper methods to interact with different types of select
 * controls shown on the page: - a react-style searchable select (custom
 * dropdown) - a standard multi-select (<select multiple>) - a legacy/old-style
 * single select element
 *
 * The methods wait for elements to be clickable before interacting to make
 * tests more stable against timing issues.
 */
public class SelectMenuPage {
	// WebDriver instance used for all interactions on this page
	WebDriver driver;

	// Reusable explicit wait for element synchronization (5 seconds)
	WebDriverWait wait;

	// Custom/react-style dropdown used to select a value
	@FindBy(xpath = "(//div[@class='css-19bb58m'])[1]")
	private WebElement selectValueDropdown;

	// Specific option inside the react-style dropdown (Group 1, option 1)
	@FindBy(xpath = "//div[contains(text(),'Group 1, option 1')]")
	private WebElement selectValueOption1;

	// Standard HTML <select> element that supports multiple selections (id="cars")
	@FindBy(id = "cars")
	private WebElement stdMultiSelect;

	// Legacy/old-style single <select> element (id="oldSelectMenu")
	@FindBy(id = "oldSelectMenu")
	private WebElement oldStyleSelectMenu;

	// Placeholder element for a different react-style multi-select control
	@FindBy(xpath = "//div[@id='react-select-4-placeholder']")
	private WebElement multiSelectDropdown;

	/**
	 * Constructor initializes the page object and PageFactory elements.
	 *
	 * @param driver the WebDriver instance driving the browser
	 */
	public SelectMenuPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Selects the specific value "Group 1, option 1" from the custom dropdown. This
	 * opens the dropdown then clicks the option.
	 */
	public void selectValueOption1() {
		// Open the dropdown and then click the desired option when clickable
		wait.until(ExpectedConditions.elementToBeClickable(selectValueDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectValueOption1)).click();
	}

	/**
	 * Selects multiple options from a standard multi-select element. The method
	 * assumes the multi-select supports selecting by visible text. It selects
	 * "Volvo" and "Saab" as part of the happy-path test.
	 */
	public void selectMultipleOptions() {
		// Ensure the <select> is ready before creating the Select helper
		wait.until(ExpectedConditions.elementToBeClickable(stdMultiSelect));
		Select select = new Select(stdMultiSelect);
		// Select two values by their visible text
		select.selectByVisibleText("Volvo");
		select.selectByVisibleText("Saab");
	}

	/**
	 * Selects a value from an old-style single <select> by visible text.
	 *
	 * @param color the visible text of the option to select (e.g. "Blue")
	 */
	public void selectOldStyleOption(String color) {
		// Wait for the control to be interactable then select by visible text
		wait.until(ExpectedConditions.elementToBeClickable(oldStyleSelectMenu));
		Select select = new Select(oldStyleSelectMenu);
		select.selectByVisibleText(color);
	}

	/**
	 * Clicks the react-style multi-select placeholder to open its options. Uses
	 * Actions to move to the element to avoid potential overlay/visibility issues.
	 */
	public void selectMultiSelectDropdownOption() {
		wait.until(ExpectedConditions.elementToBeClickable(multiSelectDropdown));
		Actions actions = new Actions(driver);
		// Move to the element and click — helps when element is off-screen or
		// overlapped
		actions.moveToElement(multiSelectDropdown).click().perform();
	}

	/**
	 * Verifies that the react-style multi-select control contains at least one
	 * option. This method performs a DOM query for the option nodes and asserts the
	 * count > 0.
	 *
	 * Note: this does not return the option texts, only asserts their existence. If
	 * tests require the actual values, extend this method to return the list.
	 */
	public void verifyMultiSelectDropdownOptions() {
		List<WebElement> options = driver.findElements(By.xpath("//div[contains(@id,'react-select-4-option-')]"));
		System.out.println("Number of options found: " + options.size());
		Assert.assertTrue(options.size() > 0, "No options found in multi-select dropdown");
	}

	public By getMultiSelectOptionsLocator(String color) {
		return By.xpath("//div[contains(@id,'react-select-4-option-') and contains(text(),'" + color + "')]");
	}

	public void selectMultiSelectOptionByColor(String color) {
		wait.until(ExpectedConditions.elementToBeClickable(getMultiSelectOptionsLocator(color))).click();
	}
	
	public boolean verifyMultiSeelectedOptionSelected(String color) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(getSelectedMultiSelectOptionalLocator(color))).isDisplayed();
		
	}
	
	public By getSelectedMultiSelectOptionalLocator(String color) {
		return By.xpath("//div[contains(@class,'css-9jq23d') and contains(text(),'" + color + "')]");
	}

}