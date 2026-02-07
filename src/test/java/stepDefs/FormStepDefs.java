package stepDefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.FormPage;
import utils.DriverFactory;
import utils.OllamaClient;
import utils.TestDataGenerator;

public class FormStepDefs {

	public WebDriver driver;
	FormPage formPage;

	public FormStepDefs() {
		this.driver = DriverFactory.getDriver();
		formPage = new FormPage(driver);
	}

	@When("I enter first name")
	public void i_enter_first_name() {
		formPage.enterFirstName(TestDataGenerator.randomFirstName());
	}

	@And("I enter last name")
	public void i_enter_last_name() {
		formPage.enterLastName(TestDataGenerator.randomLastName());
	}

	@And("I enter email")
	public void i_enter_email() {
		formPage.enterEmail(TestDataGenerator.randomEmail());
	}

	@And("I select gender")
	public void i_select_gender() {
		formPage.selectGender(TestDataGenerator.randomGender());
	}

	@And("I enter mobile number")
	public void i_enter_mobile_number() {
		formPage.enterMobileNumber(TestDataGenerator.randomMobileNumber());
	}

	@And("I enter date of birth")
	public void i_enter_date_of_birth() {
		formPage.enterDateOfBirth(TestDataGenerator.randomDateOfBirth());
	}

	@And("I enter subjects")
	public void i_select_subjects() {
		formPage.selectSubjects(TestDataGenerator.randomSubject());// need some work on this method to select multiple subjects
	}

	@And("I select hobbies")
	public void i_select_hobbies() {
		formPage.selectHobbies(TestDataGenerator.randomHobby()); // need some work on this method to select multiple hobbies
	}

	@And("I upload picture {string}")
	public void i_upload_picture(String pathToTestImage) {
		formPage.uploadPicture(pathToTestImage);
	}

	@And("I enter current address")
	public void i_enter_current_address() {
		formPage.enterCurrentAddress(TestDataGenerator.randomAddress());
	}

	@And("I select state and city")
	public void i_select_state_and_city() {
		formPage.selectStateAndCity(TestDataGenerator.randomCity(), TestDataGenerator.randomState());
	}

	@And("I submit the form")
	public void i_submit_the_form() {
		formPage.submitForm();
	}

}
