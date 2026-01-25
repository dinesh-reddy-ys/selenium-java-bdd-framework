package stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.FramesPage;
import utils.DriverFactory;

public class Frames {
public WebDriver driver;
FramesPage framesPage;

public Frames(){
    this.driver = DriverFactory.getDriver();
    framesPage= new FramesPage(driver);
}

@When("I switch to frame1")
    public void i_switches_to_frame(){
    framesPage.switchToFrameById();
}
@Then("I get the text")
    public void i_get_the_text(){

}
}
