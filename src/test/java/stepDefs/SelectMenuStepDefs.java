package stepDefs;

import io.cucumber.java.en.When;
import pages.SelectMenuPage;
import utils.DriverFactory;

public class SelectMenuStepDefs {
public SelectMenuPage selectMenuPage;
    
 public SelectMenuStepDefs() {
	 selectMenuPage = new SelectMenuPage(DriverFactory.getDriver());
 }
 
 @When("I select a option from dropdown")
 public void i_select_a_option_from_dropdown() {
	 selectMenuPage.selectValueOption1();
 }
}
