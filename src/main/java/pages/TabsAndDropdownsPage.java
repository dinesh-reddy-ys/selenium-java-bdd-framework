package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.ITabsAndDropdownPage;

/**
 * Page object that groups tab and dropdown navigation links on the demo site.
 *
 * <p>This class centralizes all the top-level navigation options (cards, tabs,
 * and section links) that are used throughout the test suite. Each method waits
 * until the corresponding element is clickable before performing the click to
 * reduce flakiness due to timing or overlays.</p>
 */
public class TabsAndDropdownsPage implements ITabsAndDropdownPage {

	WebDriver driver;
	WebDriverWait wait;

	// --- Top-level dropdown cards ---
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

	// --- Elements section tabs ---
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

	// --- Forms section tab ---
	@FindBy(xpath = "//*[text() = \"Practice Form\"]")
	private WebElement practiceFormTab;

	// --- Alerts, Frame & Windows section tabs ---
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

	// --- Widgets section tabs ---
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

	// --- Interactions section tabs ---
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

	// --- Book Store Application section tabs ---
	@FindBy(xpath = "//*[text() = \"Login\"]")
	private WebElement loginTab;
	@FindBy(xpath = "//*[text() = \"Book Store\"]")
	private WebElement bookStoreTab;
	@FindBy(xpath = "//*[text() = \"Profile\"]")
	private WebElement profileTab;
	@FindBy(xpath = "/*[text() = \"Book Store API\"]")
	private WebElement bookStoreApiTab;

	/**
	 * Constructor - initializes PageFactory elements and an explicit wait helper.
	 *
	 * @param driver the WebDriver instance used for interactions
	 */
	public TabsAndDropdownsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// --- Dropdown click actions (top-level cards) ---
	/**
	 * Clicks on the Elements dropdown card, waits until clickable.
	 */
	public void clickOnElementsDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(elementsDropdown));
		elementsDropdown.click();

	}

	/**
	 * Clicks on the Forms dropdown card, waits until clickable.
	 */
	public void clickOnFormsDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(formsDropdown));
		formsDropdown.click();
	}

	/**
	 * Clicks on the Alerts, Frames & Windows dropdown card, waits until clickable.
	 */
	public void clickOnAlertsFramewindowsDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(alertsFramesWindowsDropdown));
		alertsFramesWindowsDropdown.click();

	}

	/**
	 * Clicks on the Widgets dropdown card, waits until clickable.
	 */
	public void clickOnWidgetsDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(widgetsDropdown));
		widgetsDropdown.click();
	}

	/**
	 * Clicks on the Interactions dropdown card, waits until clickable.
	 */
	public void clickOnInteractionsDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(interactionsDropdown));
		interactionsDropdown.click();
	}

	/**
	 * Clicks on the Book Store Application dropdown card, waits until clickable.
	 */
	public void clickOnBookStoreApplicationDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(bookStoreApplicationDropdown));
		bookStoreApplicationDropdown.click();
	}

	// --- Elements tab click actions ---
	/**
	 * Clicks on the Text Box tab, waits until clickable.
	 */
	public void clickOnTextBoxTab() {
		wait.until(ExpectedConditions.elementToBeClickable(textBoxTab));
		textBoxTab.click();

	}

	/**
	 * Clicks on the Check Box tab, waits until clickable.
	 */
	public void clickOnCheckBoxTab() {
		wait.until(ExpectedConditions.elementToBeClickable(checkBoxTab));
		checkBoxTab.click();
	}

	/**
	 * Clicks on the Radio Button tab, waits until clickable.
	 */
	public void clickOnRadioButtontab() {

		wait.until(ExpectedConditions.elementToBeClickable(radioButtonTab));
		radioButtonTab.click();
	}

	/**
	 * Clicks on the Web Tables tab, waits until clickable.
	 */
	public void clickOnWebTablestab() {

		wait.until(ExpectedConditions.elementToBeClickable(webTablesTab));
		webTablesTab.click();
	}

	/**
	 * Clicks on the Buttons tab, waits until clickable.
	 */
	public void clickOnButtonsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonsTab));
		buttonsTab.click();
	}

	/**
	 * Clicks on the Links tab, waits until clickable.
	 */
	public void clickOnLinksTab() {
		wait.until(ExpectedConditions.elementToBeClickable(linksTab));
		linksTab.click();
	}

	/**
	 * Clicks on the Upload and Download tab, waits until clickable.
	 */
	public void clickOnUploadAndDownloadTab() {

		wait.until(ExpectedConditions.elementToBeClickable(uploadAndDownloadTab));
		uploadAndDownloadTab.click();
	}

	/**
	 * Clicks on the Dynamic Properties tab, waits until clickable.
	 */
	public void clickOnDynamicPropertiesTab() {

		wait.until(ExpectedConditions.elementToBeClickable(dynamicPropertiesTab));
		dynamicPropertiesTab.click();
	}

	/**
	 * Clicks on the Practice Form tab, waits until clickable.
	 */
	public void clickOnPracticeFormTab() {

		wait.until(ExpectedConditions.elementToBeClickable(practiceFormTab));
		practiceFormTab.click();
	}

	// --- Alerts, Frames & Windows tab actions ---
	/**
	 * Clicks on the Browser Windows tab, waits until clickable.
	 */
	public void clickOnBrowserWindowsTab() {

		wait.until(ExpectedConditions.elementToBeClickable(browserWindowsTab));
		browserWindowsTab.click();
	}

	/**
	 * Clicks on the Alerts tab, waits until clickable.
	 */
	public void clickOnAlertsTab() {

		wait.until(ExpectedConditions.elementToBeClickable(alertsTab));
		alertsTab.click();
	}

	/**
	 * Clicks on the Frames tab, waits until clickable.
	 */
	public void clickOnFramesTab() {

		wait.until(ExpectedConditions.elementToBeClickable(framesTab));
		framesTab.click();
	}

	/**
	 * Clicks on the Nested Frames tab, waits until clickable.
	 */
	public void clickOnNestedFramesTab() {

		wait.until(ExpectedConditions.elementToBeClickable(nestedFramesTab));
		nestedFramesTab.click();
	}

	/**
	 * Clicks on the Modal Dialogs tab, waits until clickable.
	 */
	public void clickOnModalDialogsTab() {

		wait.until(ExpectedConditions.elementToBeClickable(modalDialogsTab));
		modalDialogsTab.click();
	}

	// --- Widgets tab actions ---
	/**
	 * Clicks on the Accordian tab, waits until clickable.
	 */
	public void clickOnAccordianTab() {

		wait.until(ExpectedConditions.elementToBeClickable(accordianTab));
		accordianTab.click();
	}

	/**
	 * Clicks on the Auto Complete tab, waits until clickable.
	 */
	public void clickOnAutoCompleteTab() {

		wait.until(ExpectedConditions.elementToBeClickable(autoCompleteTab));
		autoCompleteTab.click();
	}

	/**
	 * Clicks on the Date Picker tab, waits until clickable.
	 */
	public void clickOnDatePickerTab() {

		wait.until(ExpectedConditions.elementToBeClickable(datePickerTab));
		datePickerTab.click();
	}

	/**
	 * Clicks on the Slider tab, waits until clickable.
	 */
	public void clickOnSliderTab() {

		wait.until(ExpectedConditions.elementToBeClickable(sliderTab));
		sliderTab.click();
	}

	/**
	 * Clicks on the Progress Bar tab, waits until clickable.
	 */
	public void clickOnProgressBarTab() {

		wait.until(ExpectedConditions.elementToBeClickable(progressBarTab));
		progressBarTab.click();
	}

	/**
	 * Clicks on the Tabs tab, waits until clickable.
	 */
	public void clickOnTabsTab() {

		wait.until(ExpectedConditions.elementToBeClickable(tabsTab));
		tabsTab.click();
	}

	/**
	 * Clicks on the Tool Tips tab, waits until clickable.
	 */
	public void clickOnToolTipsTab() {

		wait.until(ExpectedConditions.elementToBeClickable(toolTipsTab));
		toolTipsTab.click();
	}

	/**
	 * Clicks on the Menu tab, waits until clickable.
	 */
	public void clickOnMenuTab() {

		wait.until(ExpectedConditions.elementToBeClickable(menuTab));
		menuTab.click();
	}

	/**
	 * Clicks on the Select Menu tab, waits until clickable.
	 */
	public void clickOnSelectMenuTab() {

		wait.until(ExpectedConditions.elementToBeClickable(selectMenuTab));
		selectMenuTab.click();
	}

	// --- Interactions tab actions ---
	/**
	 * Clicks on the Sortable tab, waits until clickable.
	 */
	public void clickSortableTab() {

		wait.until(ExpectedConditions.elementToBeClickable(sortableTab));
		sortableTab.click();
	}

	/**
	 * Clicks on the Selectable tab, waits until clickable.
	 */
	public void clickSelectableTab() {

		wait.until(ExpectedConditions.elementToBeClickable(selectableTab));
		selectableTab.click();
	}

	/**
	 * Clicks on the Resizable tab, waits until clickable.
	 */
	public void clickResizableTab() {

		wait.until(ExpectedConditions.elementToBeClickable(resizableTab));
		resizableTab.click();
	}

	/**
	 * Clicks on the Droppable tab, waits until clickable.
	 */
	public void clickDroppableTab() {

		wait.until(ExpectedConditions.elementToBeClickable(droppableTab));
		droppableTab.click();
	}

	/**
	 * Clicks on the Dragabble tab, waits until clickable.
	 */
	public void clickDragabbleTab() {

		wait.until(ExpectedConditions.elementToBeClickable(dragabbleTab));
		dragabbleTab.click();
	}

	// --- Book Store Application tab actions ---
	/**
	 * Clicks on the Login tab, waits until clickable.
	 */
	public void clickOnLoginTab() {

		wait.until(ExpectedConditions.elementToBeClickable(loginTab));
		loginTab.click();
	}

	/**
	 * Clicks on the Book Store tab, waits until clickable.
	 */
	public void clickOnBookStoreTab() {

		wait.until(ExpectedConditions.elementToBeClickable(bookStoreTab));
		bookStoreTab.click();
	}

	/**
	 * Clicks on the Profile tab, waits until clickable.
	 */
	public void clickOnProfileTab() {

		wait.until(ExpectedConditions.elementToBeClickable(profileTab));
		profileTab.click();
	}

	/**
	 * Clicks on the Book Store API tab, waits until clickable.
	 */
	public void clickOnBookStoreAPI() {

		wait.until(ExpectedConditions.elementToBeClickable(bookStoreApiTab));
		bookStoreApiTab.click();
	}

}