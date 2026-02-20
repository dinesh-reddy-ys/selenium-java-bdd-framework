package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.IFlipkartPage;

public class FlipkartPage implements IFlipkartPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;

	@FindBy(css = "a-price-whole")
	private WebElement ProductPrices;

	@FindBy(xpath = "//div[@data-cy='title-recipe']//h2//span")
	private List<WebElement> productTitles;

	@FindBy(xpath = "//span[@class = 'a-price-whole']")
	private List<WebElement> priceElements;
	
	@FindBy(xpath = "//a[contains(@class, 's-pagination-next')]")
	private WebElement nextPageButton;

	public FlipkartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@Override
	public void searchForProduct(String productName) {
		wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		searchBox.clear();
		searchBox.sendKeys(productName);
		searchBox.sendKeys(Keys.ENTER);
		driver.manage().window().fullscreen();
		;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Override
	public void selectProductFromResults(String productName) {
		System.out.println("Selecting product from results: " + productName);
	}

	@Override
	public void addToCart() {
		System.out.println("Adding product to cart");
	}

	@Override
	public void clickOnCrossButton() {
		System.out.println("Clicking on cross button");
	}

	public boolean areAllTitlesContaining(String productName) {
		// Determine how many elements we can safely check
		int limit = Math.min(2, productTitles.size());

		// Loop only through first two results
		for (int i = 0; i < limit; i++) {

			String titleText = productTitles.get(i).getText().toLowerCase();

			if (!titleText.contains(productName.toLowerCase())) {
				return false;
			}
		}

		return true;
	}

	public void selectItemsWithPricesLessThan(int maxPrice) {
		for (WebElement priceElement : priceElements) {
			String priceText = priceElement.getText().replaceAll("[^0-9]", "");
			int priceValue = Integer.parseInt(priceText);
			if (priceValue < maxPrice) {
				priceElement.click();
				break;
			}
		}
	}
	
	/**
	 * Clicks on next pagination button
	 */
	
	public void clickNextPage() {
		wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
		String mainWindow = driver.getWindowHandle();
		nextPageButton.click();
		List<String> allWindows = driver.getWindowHandles().stream().toList();
		for(String window : allWindows) {
			if(!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
			
		}
		
		wait.until(ExpectedConditions.visibilityOfAllElements(priceElements));
	}

}
