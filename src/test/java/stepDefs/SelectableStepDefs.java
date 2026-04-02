package stepDefs;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SelectablePage;

public class SelectableStepDefs {
	
	SelectablePage selectablePage;
	
	public SelectableStepDefs() {
		selectablePage = new SelectablePage(utils.DriverFactory.getDriver());
	}
	
	@When("I select below values")
	public void i_select_below_values(DataTable dataTable) {
		List<String> items = dataTable.asList();
		for(String item : items) {
			selectablePage.selectListItem(item);
		}
		
	}
	
	@When("I click on {string} tab")
	public void i_click_on_tab(String tabName) {
		selectablePage.clickOnTab(tabName);
	}
	
	@Then("I should see the selected values in the list")
	public void i_should_see_the_selected_values_in_the_list(DataTable dataTable) {
		List<String> items = dataTable.asList();
		for(String item : items) {
			selectablePage.isListItemSelected(item);
		}
	}
	

}
