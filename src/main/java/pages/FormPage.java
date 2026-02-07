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

public class FormPage implements IFormPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "firstName")
	private WebElement firstNameInput;

	@FindBy(id = "lastName")
	private WebElement lastNameInput;

	@FindBy(id = "userEmail")
	private WebElement emailInput;

	@FindBy(xpath = "//label[@for='gender-radio-1']")
	private WebElement genderMaleRadio;

	@FindBy(xpath = "//label[@for='gender-radio-1']")
	private WebElement genderFemaleRadio;

	@FindBy(xpath = "//label[@for='gender-radio-1']")
	private WebElement genderOtherRadio;

	@FindBy(id = "userNumber")
	private WebElement mobileNumberInput;

	@FindBy(id = "dateOfBirthInput")
	private WebElement dateOfBirthInput;

	@FindBy(id = "subjectsInput")
	private WebElement subjectsInput;

	@FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
	private WebElement hobbiesSportsCheckbox;

	@FindBy(xpath = "//label[@for='hobbies-checkbox-2']")
	private WebElement hobbiesReadingCheckbox;

	@FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
	private WebElement hobbiesMusicCheckbox;

	@FindBy(id = "uploadPicture")
	private WebElement uploadPictureInput;

	@FindBy(id = "currentAddress")
	private WebElement currentAddressInput;

	@FindBy(xpath = "//input[@id='react-select-6-inp")
	private WebElement stateDropdown;

	@FindBy(xpath = "//div[@id='city']")
	private WebElement cityDropdown;

	@FindBy(id = "submit")
	private WebElement submitButton;

	public FormPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	@Override
	public void enterFirstName(String firstName) {
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
		wait.until(ExpectedConditions.visibilityOf(dateOfBirthInput));
		dateOfBirthInput.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='';", dateOfBirthInput);
		// Small pause helps React update
		dateOfBirthInput.sendKeys(dateOfBirth);
		dateOfBirthInput.sendKeys(Keys.ENTER);
	}

	@Override
	public void selectSubjects(String subjects) {
		ScrollUtils.scrollToElement(driver, subjectsInput);
		wait.until(ExpectedConditions.visibilityOf(subjectsInput));
		subjectsInput.click();
		subjectsInput.sendKeys(subjects);
		subjectsInput.sendKeys(Keys.ENTER);
	}

	@Override
	public void selectHobbies(String hobbies) {
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
		wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
		stateDropdown.click();
		stateDropdown.sendKeys(state);
		stateDropdown.sendKeys(Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOf(cityDropdown));
		cityDropdown.click();
		cityDropdown.sendKeys(city);
		cityDropdown.sendKeys(Keys.ENTER);
	}

	@Override
	public void submitForm() {
		ScrollUtils.scrollToElement(driver, submitButton);
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
	}

}
