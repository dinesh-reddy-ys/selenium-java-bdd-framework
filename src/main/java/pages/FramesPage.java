package pages;

import interfaces.IFramesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FramesPage implements IFramesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "frame1")
    private WebElement frame1;
    @FindBy(id="sampleHeading")
    private WebElement frameText;

    // Constructor to initialize WebDriver
    public  FramesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void switchToFrameById(){
        driver.switchTo().frame(frame1);
    }

    public String getFrameText(){
       return frameText.getText();
    }
}
