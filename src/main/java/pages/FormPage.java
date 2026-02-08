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

import interfaces.IFormPage;
import utils.ScrollUtils;

/**
 * Page object for interacting with the demo form page.
 *
 * <p>This class follows the Page Object Model pattern. Each WebElement is
 * located using @FindBy and exposed operations are provided as simple
 * methods that encapsulate waiting and interaction logic. Methods try to
 * avoid flakiness by waiting for visibility/clickability and by using
 * JavaScript-based clearing where native clear() may not be reliable with
 * certain front-end frameworks (like React).</p>
 */
public class FormPage implements IFormPage {

	WebDriver driver;
	WebDriverWait wait;

	// Text input for first name
	@FindBy(id = "firstName")
	private WebElement firstNameInput;

	// Text input for last name
	@FindBy(id = "lastName")
	private WebElement lastNameInput;

	// Text input for email
	@FindBy(id = "userEmail")
	private WebElement emailInput;

	// Note: the original file used the same XPath for all gender labels.
	// These annotations currently point to the same locator (gender-radio-1).
	// If the page actually has separate locators for male/female/other, update
	// these to the correct values. For now we document the duplication.
	@FindBy(xpath = "//label[@for='gender-radio-1']")
	private WebElement genderMaleRadio;

	@FindBy(xpath = "//label[@for='gender-radio-1']")
	private WebElement genderFemaleRadio;

	@FindBy(xpath = "//label[@for='gender-radio-1']")
	private WebElement genderOtherRadio;

	// Mobile number input
	@FindBy(id = "userNumber")
	private WebElement mobileNumberInput;

	// Date of birth input for date picker interactions
	@FindBy(id = "dateOfBirthInput")
	private WebElement dateOfBirthInput;

	// Subjects input (typeahead). We'll click, type, and press ENTER.
	@FindBy(id = "subjectsInput")
	private WebElement subjectsInput;

	// Hobbies checkboxes (note: one locator is duplicated in original file)
	@FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
	private WebElement hobbiesSportsCheckbox;

	@FindBy(xpath = "//label[@for='hobbies-checkbox-2']")
	private WebElement hobbiesReadingCheckbox;

	@FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
	private WebElement hobbiesMusicCheckbox;

	// File upload input (use sendKeys with the absolute file path)
	@FindBy(id = "uploadPicture")
	private WebElement uploadPictureInput;

	// Current address textarea/input
	@FindBy(id = "currentAddress")
	private WebElement currentAddressInput;

	// State dropdown input (note: original XPath is missing a closing quote)
	// Verify this locator if state selection fails in tests.
	@FindBy(xpath = "//input[@id='react-select-6-inp")
	private WebElement stateDropdown;

	// City dropdown container
	@FindBy(xpath = "//div[@id='city']")
	private WebElement cityDropdown;

	// Submit button for the form
	@FindBy(id = "submit")
	private WebElement submitButton;

	/**
	 * Constructor - initialize the WebDriver, explicit wait and PageFactory
	 * elements.
	 *
	 * @param driver the WebDriver instance to use
	 */
	public FormPage(WebDriver driver) {
		this.driver = driver;
		// Use a 20 second explicit wait to cover slower environments
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	@Override
	public void enterFirstName(String firstName) {
		// Wait for visibility, clear existing text and type the value
		wait.until(ExpectedConditions.visibilityOf(firstNameInput));
		firstNameInput.clear();
		firstNameInput.sendKeys(firstName);
	}

	@Override
	public void enterLastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOf(lastNameInput));
		lastNameInput.clear();
		lastNameInput.sendKeys(lastName);
	}

	@Override
	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(emailInput));
		emailInput.clear();
		emailInput.sendKeys(email);
	}

	@Override
	public void selectGender(String gender) {
		// Normalize the input and click the corresponding label.
		// Because the original locators are duplicated, all cases use the
		// same locator. If tests behave unexpectedly, update the locators
		// above to point to the correct radio labels.
		switch (gender.toLowerCase()) {
		case "male":
			wait.until(ExpectedConditions.elementToBeClickable(genderMaleRadio));
			genderMaleRadio.click();
			break;
		case "female":
			genderFemaleRadio.click();
			break;
		case "other":
			genderOtherRadio.click();
			break;
		default:
			genderMaleRadio.click(); // Default to male
		}
	}

	@Override
	public void enterMobileNumber(String mobileNumber) {
		wait.until(ExpectedConditions.visibilityOf(mobileNumberInput));
		mobileNumberInput.clear();
		mobileNumberInput.sendKeys(mobileNumber);
	}

	@Override
	public void enterDateOfBirth(String dateOfBirth) {
		// Click, clear using JS (safer for controlled inputs), type and submit
		wait.until(ExpectedConditions.visibilityOf(dateOfBirthInput));
		dateOfBirthInput.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='';", dateOfBirthInput);
		// Small pause helps front-end frameworks detect changes
		dateOfBirthInput.sendKeys(dateOfBirth);
		dateOfBirthInput.sendKeys(Keys.ENTER);
	}

	@Override
	public void selectSubjects(String subjects) {
		// Scroll into view and type the subject then press ENTER to select
		ScrollUtils.scrollToElement(driver, subjectsInput);
		wait.until(ExpectedConditions.visibilityOf(subjectsInput));
		subjectsInput.click();
		subjectsInput.sendKeys(subjects);
		subjectsInput.sendKeys(Keys.ENTER);
	}

	@Override
	public void selectHobbies(String hobbies) {
		// The input may contain multiple hobbies separated by commas. Split and
		// click each corresponding checkbox. Scroll each into view to avoid
		// overlay issues.
		String[] hobbiesArray = hobbies.split(",");
		for (String hobby : hobbiesArray) {
			switch (hobby.trim().toLowerCase()) {
			case "sports":
				ScrollUtils.scrollToElement(driver, hobbiesSportsCheckbox);
				hobbiesSportsCheckbox.click();
				break;
			case "reading":
				ScrollUtils.scrollToElement(driver, hobbiesReadingCheckbox);
				hobbiesReadingCheckbox.click();
				break;
			case "music":
				ScrollUtils.scrollToElement(driver, hobbiesMusicCheckbox);
				hobbiesMusicCheckbox.click();
				break;
			default:
				ScrollUtils.scrollToElement(driver, hobbiesSportsCheckbox);
				hobbiesSportsCheckbox.click(); // Default to sports
			}
		}
	}

	@Override
	public void uploadPicture(String picturePath) {
		// Upload input accepts a file path; use absolute path for reliability
		wait.until(ExpectedConditions.visibilityOf(uploadPictureInput));
		uploadPictureInput.sendKeys(picturePath);
	}

	@Override
	public void enterCurrentAddress(String currentAddress) {
		wait.until(ExpectedConditions.visibilityOf(currentAddressInput));
		currentAddressInput.clear();
		currentAddressInput.sendKeys(currentAddress);
	}

	@Override
	public void selectStateAndCity(String state, String city) {
		// Click the state input, type the state, and press ENTER to select
		wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
		stateDropdown.click();
		stateDropdown.sendKeys(state);
		stateDropdown.sendKeys(Keys.ENTER);

		// Wait for city options and select the city similarly
		wait.until(ExpectedConditions.visibilityOf(cityDropdown));
		cityDropdown.click();
		cityDropdown.sendKeys(city);
		cityDropdown.sendKeys(Keys.ENTER);
	}

	@Override
	public void submitForm() {
		// Ensure the submit button is visible and clickable, then submit
		ScrollUtils.scrollToElement(driver, submitButton);
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
	}

}
