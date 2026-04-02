package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectablePage {

	WebDriver driver;

	WebDriverWait wait;

	@FindBy(xpath = "//button[@id='demo-tab-list']")
	private WebElement listTab;
	
	@FindBy(xpath = "//button[@id='demo-tab-grid']")
	private WebElement gridTab;

	public SelectablePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void clickListTab() {
		wait.until(driver -> listTab.isDisplayed() && listTab.isEnabled());
		listTab.click();
	}
	
	public void clickgridTab() {
		wait.until(driver -> gridTab.isDisplayed() && gridTab.isEnabled());
		gridTab.click();
	}
	
	public void clickOnTab(String tabName) {
		WebElement tab = driver.findElement(By.xpath("//button[contains(@id,'demo-tab-" + tabName + "')]"));
		wait.until(driver -> tab.isDisplayed() && tab.isEnabled());
		tab.click();
	}
	
	public void selectListItem(String itemText) {
		WebElement listItem = driver.findElement(
				By.xpath("//li[contains(@class,'list-group-item-action') and contains(text(),'" + itemText  +"')]"));
		wait.until(driver -> listItem.isDisplayed() && listItem.isEnabled());
		listItem.click();
	}
	
	public void isListItemSelected(String itemText) {
		WebElement listItem = driver.findElement(
				By.xpath("//li[contains(@class,'list-group-item-action') and contains(text(),'" + itemText  +"')]"));
		wait.until(driver -> listItem.isDisplayed());
		if(!listItem.getAttribute("class").contains("active")) {
			throw new AssertionError("Expected item '" + itemText + "' to be selected, but it was not.");
		}
	}

}
