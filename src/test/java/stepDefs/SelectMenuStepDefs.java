package stepDefs;

import org.testng.Assert;

import io.cucumber.java.en.And;
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
 
 @And("I select {string} from multiselect dropdown")
 public void i_select_from_multiselect_dropdown(String option) {
	 selectMenuPage.selectMultiSelectOptionByColor(option);
 }
 
 @Then("{string} should be selected in multiselect dropdown")
 public void should_be_selected_in_multiselect_dropdown(String option) {
	 Assert.assertTrue(selectMenuPage.verifyMultiSelectedOptionSelected(option), option + " should be selected in the multi-select dropdown");	 
 }
 
 @And("I remove {string} from multiselect dropdown")
 public void i_remove_from_multiselect_dropdown(String option) {
	 selectMenuPage.removeMultiSlectOptionByColor(option);
 }
 
 @Then("{string} should not be selected in multiselect dropdown")
 public void should_not_be_selected_in_multiselect_dropdown(String option) {
	 Assert.assertFalse(selectMenuPage.verifyMultiSelectedOptionSelected(option), option +" should not be selected in the multi-select dropdown");
 }
}
