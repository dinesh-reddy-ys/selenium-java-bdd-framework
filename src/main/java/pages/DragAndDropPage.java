package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IDragAndDropPage;

public class DragAndDropPage implements IDragAndDropPage {
	
	WebDriver driver;
	
	WebDriverWait wait;
	
	@FindBy(id = "draggable")
	private WebElement draggable;
	
	@FindBy(id = "droppable")
	private WebElement droppable;
	
	public DragAndDropPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		}
	
	public void dragAndDrop() {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(droppable, draggable).perform();
	}
	
	public String getDroppableText() {
		return droppable.getText();
	}
	
	

}
