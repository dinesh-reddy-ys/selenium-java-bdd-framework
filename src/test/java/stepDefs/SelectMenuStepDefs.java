package stepDefs;

import io.cucumber.java.en.Then;
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
 
 @When("I select multiple values from dropdown")
 public void i_select_multiple_values_from_dropdown() {
	 selectMenuPage.selectMultipleOptions();
 }
 
 @When("I select {string} from old style dropdown")
 public void i_select_from_old_style_dropdown(String color) {
	 selectMenuPage.selectOldStyleOption(color);
 }
 
 @When("I click on multiselect dropdown")
 public void i_click_on_multiselect_dropdown() {
	 selectMenuPage.selectMultiSelectDropdownOption();
 }
 
 @Then("dropdown options should be visible")
 public void dropdown_options_should_be_visible() {
	 selectMenuPage.verifyMultiSelectDropdownOptions();
 }
}
