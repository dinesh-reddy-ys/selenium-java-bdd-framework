package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.ITabsAndDropdownPage;

public class TabsAndDropdownsPage implements ITabsAndDropdownPage {
	
	WebDriver driver;
	WebDriverWait wait;

@FindBy(xpath = "//*[text() = \"Elements\"]")
private WebElement elementsDropdown;
@FindBy(xpath = "//*[text() = \"Forms\"]")
private WebElement formsDropdown;
@FindBy(xpath = "//*[text() = \"Alerts, Frames & Windows\"]")
private WebElement alertsFramesWindowsDropdown;
@FindBy(xpath = "//*[text() = \"Widgets\"]")
private WebElement widgetsDropdown;
@FindBy(xpath = "//*[text() = \"Interactions\"]")
private WebElement interactionsDropdown;
@FindBy(xpath = "//*[text() = \"Book Store Application\"]")
private WebElement bookStoreApplicationDropdown;

@FindBy(xpath = "//*[text() = \"Text Box\"]")
private WebElement textBoxTab;
@FindBy(xpath = "//*[text() = \"Check Box\"]")
private WebElement checkBoxTab;
@FindBy(xpath = "//*[text() = \"radio Button\"]")
private WebElement radioButtonTab;
@FindBy(xpath = "//*[text() = \"Web tables\"]")
private WebElement webTablesTab;
@FindBy(xpath = "//*[text() = \"Buttons\"]")
private WebElement buttonsTab;
@FindBy(xpath = "//*[text() = \"Links\"]")
private WebElement linksTab;
@FindBy(xpath = "//*[text() = \"Upload and Download\"]")
private WebElement uploadAndDownloadTab;
@FindBy(xpath = "//*[text() = \"Dynamic Properties\"]")
private WebElement dynamicPropertiesTab;

@FindBy(xpath = "//*[text() = \"Practice Form\"]")
private WebElement practiceFormTab;

@FindBy(xpath = "//*[text() = \"Browser Windows\"]")
private WebElement browserWindowsTab;
@FindBy(xpath = "//*[text() = \"Alerts\"]")
private WebElement alertsTab;
@FindBy(xpath = "//*[text() = \"Frames\"]")
private WebElement framesTab;
@FindBy(xpath = "//*[text() = \"Nested Frames\"]")
private WebElement nestedFramesTab;
@FindBy(xpath = "//*[text() = \"Modal Dialogs\"]")
private WebElement modalDialogsTab;

@FindBy(xpath = "//*[text() = \"Accordian\"]")
private WebElement accordianTab;
@FindBy(xpath = "//*[text() = \"Auto Complete\"]")
private WebElement autoCompleteTab;
@FindBy(xpath = "//*[text() = \"Date Picker\"]")
private WebElement datePickerTab;
@FindBy(xpath = "//*[text() = \"Slider\"]")
private WebElement sliderTab;
@FindBy(xpath = "//*[text() = \"progress Bar\"]")
private WebElement progressBarTab;
@FindBy(xpath = "//*[text() = \"Tabs\"]")
private WebElement tabsTab;
@FindBy(xpath = "//*[text() = \"Tool Tips\"]")
private WebElement toolTipsTab;
@FindBy(xpath = "//*[text() = \"Menu\"]")
private WebElement menuTab;
@FindBy(xpath = "//*[text() = \"Select Menu\"]")
private WebElement selectMenuTab;

@FindBy(xpath = "//*[text() = \"Sortable\"]")
private WebElement sortableTab;
@FindBy(xpath = "//*[text() = \"Selectable\"]")
private WebElement selectableTab;
@FindBy(xpath = "//*[text() = \"Resizable\"]")
private WebElement resizableTab;
@FindBy(xpath = "//*[text() = \"Droppable\"]")
private WebElement droppableTab;
@FindBy(xpath = "//*[text() = \"Dragabble\"]")
private WebElement dragabbleTab;

@FindBy(xpath = "//*[text() = \"Login\"]")
private WebElement loginTab;
@FindBy(xpath = "//*[text() = \"Book Store\"]")
private WebElement bookStoreTab;
@FindBy(xpath = "//*[text() = \"Profile\"]")
private WebElement profileTab;
@FindBy(xpath = "/*[text() = \"Book Store API\"]")
private WebElement bookStoreApiTab;


public TabsAndDropdownsPage(WebDriver driver) {
	this.driver = driver;
	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	PageFactory.initElements(driver,this);
	}


public void clickOnElementsDropdown() {
	wait.until(ExpectedConditions.elementToBeClickable(elementsDropdown));
	elementsDropdown.click();
	
}
public void clickOnFormsDropdown() {
	wait.until(ExpectedConditions.elementToBeClickable(formsDropdown));
	formsDropdown.click();
	}
public void clickOnAlertsFramewindowsDropdown() {
	wait.until(ExpectedConditions.elementToBeClickable(alertsFramesWindowsDropdown));
	alertsFramesWindowsDropdown.click();
	
}
public void clickOnWidgetsDropdown() {
	wait.until(ExpectedConditions.elementToBeClickable(widgetsDropdown));
	widgetsDropdown.click();
	}
public void clickOnInteractionsDropdown() {
	wait.until(ExpectedConditions.elementToBeClickable(interactionsDropdown));
	interactionsDropdown.click();
}

public void clickOnBookStoreApplicationDropdown() {
	wait.until(ExpectedConditions.elementToBeClickable(bookStoreApplicationDropdown));
	bookStoreApplicationDropdown.click();
	}


public void clickOnTextBoxTab() {
	wait.until(ExpectedConditions.elementToBeClickable(textBoxTab));
	textBoxTab.click();
	
}

public void clickOnCheckBoxTab() {
	wait.until(ExpectedConditions.elementToBeClickable(checkBoxTab));
	checkBoxTab.click();
	}

public void clickOnRadioButtontab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(radioButtonTab));
	radioButtonTab.click();
	}

public void clickOnWebTablestab() {	

	wait.until(ExpectedConditions.elementToBeClickable(webTablesTab));
	webTablesTab.click();
	}

public void clickOnButtonsTab() {
	wait.until(ExpectedConditions.elementToBeClickable(buttonsTab));
	buttonsTab.click();
	}

public void clickOnLinksTab() {
	wait.until(ExpectedConditions.elementToBeClickable(linksTab));
	linksTab.click();
	}

public void clickOnUploadAndDownloadTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(uploadAndDownloadTab));
	uploadAndDownloadTab.click();
	}

public void clickOnDynamicPropertiesTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(dynamicPropertiesTab));
	dynamicPropertiesTab.click();
	}

public void clickOnPracticeFormTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(practiceFormTab));
	practiceFormTab.click();
	}

public void clickOnBrowserWindowsTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(browserWindowsTab));
	browserWindowsTab.click();
	}

public void clickOnAlertsTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(alertsTab));
	alertsTab.click();
	}

public void clickOnFramesTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(framesTab));
	framesTab.click();
	}

public void clickOnNestedFramesTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(nestedFramesTab));
	nestedFramesTab.click();
	}

public void clickOnModalDialogsTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(modalDialogsTab));
	modalDialogsTab.click();
	}

public void clickOnAccordianTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(accordianTab));
	accordianTab.click();
	}

public void clickOnAutoCompleteTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(autoCompleteTab));
	autoCompleteTab.click();
	}

public void clickOnDatePickerTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(datePickerTab));
	datePickerTab.click();
	}

public void clickOnSliderTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(sliderTab));
	sliderTab.click();
	}

public void clickOnProgressBarTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(progressBarTab));
	progressBarTab.click();
	}
public void clickOnTabsTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(tabsTab));
	tabsTab.click();
	}

public void clickOnToolTipsTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(toolTipsTab));
	toolTipsTab.click();
	}

public void clickOnMenuTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(menuTab));
	menuTab.click();
	}

public void clickOnSelectMenuTab() {
	

	wait.until(ExpectedConditions.elementToBeClickable(selectMenuTab));
	selectMenuTab.click();
	}

public void clickSortableTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(sortableTab));
	sortableTab.click();
	}

public void clickSelectableTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(selectableTab));
	selectableTab.click();
	}

public void clickResizableTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(resizableTab));
	resizableTab.click();
	}

public void clickDroppableTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(droppableTab));
	droppableTab.click();
	}

public void clickDragabbleTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(dragabbleTab));
	dragabbleTab.click();
	}

public void clickOnLoginTab() {

	wait.until(ExpectedConditions.elementToBeClickable(loginTab));
	loginTab.click();
	}

public void clickOnBookStoreTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(bookStoreTab));
	bookStoreTab.click();
	}

public void clickOnProfileTab() {
	
	wait.until(ExpectedConditions.elementToBeClickable(profileTab));
	profileTab.click();
	}
public void clickOnBookStoreAPI() {
	
	wait.until(ExpectedConditions.elementToBeClickable(bookStoreApiTab));
	bookStoreApiTab.click();
	}


}
