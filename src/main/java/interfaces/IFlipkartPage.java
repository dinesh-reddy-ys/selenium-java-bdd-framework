package interfaces;

public interface IFlipkartPage {
	
	void searchForProduct(String productName);
	void selectProductFromResults(String productName);
	void addToCart();
	void clickOnCrossButton();
	

}
