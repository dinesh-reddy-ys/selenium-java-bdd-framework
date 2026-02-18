package stepDefs;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProfilePage;
import utils.DriverFactory;

public class LoginStepDefs {
	public WebDriver driver;
	LoginPage loginPage;
	ProfilePage profilePage;

	public LoginStepDefs() {
		this.driver = DriverFactory.getDriver();
		loginPage = new LoginPage(driver);
		profilePage = new ProfilePage(driver);
	}

	@Given("I navigate to {string}")
	public void i_navigate_to_the_login_page(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		
		driver.manage().window().fullscreen();
	}

	@When("I enter valid username {string} and password {string} and login")
	public void i_enter_valid_username_and_password(String username, String password) {
		loginPage.clickOnUsernameField();
		loginPage.enterUsername(username);
		loginPage.clickOnPasswordField();
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
	}

	@Then("I should be able to login")
	public void i_should_be_able_to_login() {
		profilePage.profileNameIsDisplayed();
	}

}
