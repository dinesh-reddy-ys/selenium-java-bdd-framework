package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.ILoginPage;

public class LoginPage implements ILoginPage {
    // WebDriver instance to interact with the browser
    WebDriver driver;

    // WebDriverWait instance for explicit waits
    WebDriverWait wait;

    // WebElement for the username input field
    @FindBy(id = "userName")
    private WebElement usernameField;

    // WebElement for the password input field
    @FindBy(id = "password")
    private WebElement passwordField;

    // WebElement for the login button
    @FindBy(id = "login")
    private WebElement loginButton;

    // Constructor to initialize the LoginPage with WebDriver and WebDriverWait
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initialize WebElements using PageFactory
        PageFactory.initElements(driver, this);
    }

    // Method to click on the username input field
    public void clickOnUsernameField() {
        // Wait until the username field is visible and clickable
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        // Click on the username field
        usernameField.click();
    }

    // Method to click on the password input field
    public void clickOnPasswordField() {
        // Wait until the password field is visible and clickable
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        // Click on the password field
        passwordField.click();
    }

    // Method to click on the login button
    public void clickOnLoginButton() {
        // Wait until the login button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        // Click on the login button
        loginButton.click();
    }

    // Method to enter the username into the username input field
    public void enterUsername(String username) {
        // Wait until the username field is visible
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        // Enter the provided username
        usernameField.sendKeys(username);
    }

    // Method to enter the password into the password input field
    public void enterPassword(String password) {
        // Wait until the password field is visible
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        // Enter the provided password
        passwordField.sendKeys(password);
    }
}
