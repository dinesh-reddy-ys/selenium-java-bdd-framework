package pages;

import interfaces.IRegistrationFormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationFormPage implements IRegistrationFormPage {

    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "userEmail")
    private WebElement emailInput;
    @FindBy(id = "age")
    private WebElement ageInput;
    @FindBy(id = "salary")
    private WebElement salaryInput;
    @FindBy(id = "department")
    private WebElement departmentInput;
    @FindBy(id = "submit")
    private WebElement submitButton;

    public RegistrationFormPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }

    public  void enterFirstName(String firstName){
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.sendKeys(lastName);
    }
    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);
    }
    public void enterAge(String age){
        wait.until(ExpectedConditions.visibilityOf(ageInput));
        ageInput.sendKeys(age);
    }
    public void enterSalary(String salary){
        wait.until(ExpectedConditions.visibilityOf(salaryInput));
        salaryInput.sendKeys(salary);
    }
    public void enterDepartment(String department){
        wait.until(ExpectedConditions.visibilityOf(departmentInput));
        departmentInput.sendKeys(department);
    }
    public void clickOnSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
    }

}
