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

public class DatePickerPage {
   WebDriver driver;
   WebDriverWait wait;
   
   @FindBy(id = "datePickerMonthYearInput")
   private WebElement dateInputField;
   
   
   public DatePickerPage(WebDriver driver) {
	   this.driver = driver;
	   this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	   PageFactory.initElements(driver, this);
   }
   
   public void enterDate(String date) {
	   wait.until(ExpectedConditions.visibilityOf(dateInputField));
	   //dateInputField.clear();
	   dateInputField.click();
//	   dateInputField.sendKeys(Keys.CONTROL + "a");
//	   dateInputField.sendKeys(Keys.DELETE);
	// Clear value using JS (bypass React control)
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].value='';", dateInputField);

	    // Small pause helps React update
	    try { Thread.sleep(200); } catch (InterruptedException e) {}
	   dateInputField.sendKeys(date);
	   dateInputField.sendKeys(Keys.ENTER);
	   System.out.println("Date entered: " + date);
   }
   public String getDate() {
	   wait.until(ExpectedConditions.visibilityOf(dateInputField));
	   return dateInputField.getAttribute("value");
   }
   
   
}
