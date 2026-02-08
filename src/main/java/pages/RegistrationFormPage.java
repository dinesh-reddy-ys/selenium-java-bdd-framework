package pages;

import interfaces.IRegistrationFormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object for the Registration Form page used in demo tests.
 *
 * <p>This class exposes simple actions for filling out and submitting the
 * registration form. Each interaction waits for element visibility to
 * reduce flakiness caused by timing issues. The class intentionally keeps
 * methods small and focused so tests can compose them as needed.</p>
 */
public class RegistrationFormPage implements IRegistrationFormPage {

	// WebDriver instance used by this page object
	WebDriver driver;
	// Explicit wait used to wait for element conditions
	WebDriverWait wait;

	// First name input field
	@FindBy(id = "firstName")
	private WebElement firstNameInput;
	// Last name input field
	@FindBy(id = "lastName")
	private WebElement lastNameInput;
	// Email input field
	@FindBy(id = "userEmail")
	private WebElement emailInput;
	// Age input field (numeric)
	@FindBy(id = "age")
	private WebElement ageInput;
	// Salary input field (numeric)
	@FindBy(id = "salary")
	private WebElement salaryInput;
	// Department input field
	@FindBy(id = "department")
	private WebElement departmentInput;
	// Submit button for the registration form
	@FindBy(id = "submit")
	private WebElement submitButton;

	/**
	 * Constructor - initializes PageFactory elements and an explicit wait.
	 *
	 * @param driver the WebDriver instance to use for page interactions
	 */
	public RegistrationFormPage(WebDriver driver) {
		this.driver = driver;
		// Use a short explicit wait (5s) for these simple form fields; increase
		// if running in slower environments or CI.
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Enters the given first name into the first name input.
	 *
	 * @param firstName value to type into the first name field
	 */
	public void enterFirstName(String firstName) {
		// Wait until visible then type the value
		wait.until(ExpectedConditions.visibilityOf(firstNameInput));
		firstNameInput.sendKeys(firstName);
	}

	/**
	 * Enters the given last name into the last name input.
	 *
	 * @param lastName value to type into the last name field
	 */
	public void enterLastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOf(lastNameInput));
		lastNameInput.sendKeys(lastName);
	}

	/**
	 * Enters the given email into the email input.
	 *
	 * @param email value to type into the email field
	 */
	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOf(emailInput));
		emailInput.sendKeys(email);
	}

	/**
	 * Enters the given age into the age input.
	 *
	 * @param age numeric age value as a String
	 */
	public void enterAge(String age) {
		wait.until(ExpectedConditions.visibilityOf(ageInput));
		ageInput.sendKeys(age);
	}

	/**
	 * Enters the given salary into the salary input.
	 *
	 * @param salary numeric salary value as a String
	 */
	public void enterSalary(String salary) {
		wait.until(ExpectedConditions.visibilityOf(salaryInput));
		salaryInput.sendKeys(salary);
	}

	/**
	 * Enters the given department into the department input.
	 *
	 * @param department value to type into the department field
	 */
	public void enterDepartment(String department) {
		wait.until(ExpectedConditions.visibilityOf(departmentInput));
		departmentInput.sendKeys(department);
	}

	/**
	 * Clicks the Submit button to submit the registration form.
	 *
	 * <p>The method waits for the submit button to be visible to avoid clicking
	 * before it is present in the DOM.</p>
	 */
	public void clickOnSubmitButton() {
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();
	}

}