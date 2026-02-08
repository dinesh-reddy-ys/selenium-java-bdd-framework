package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility helpers for scrolling within the browser viewport.
 *
 * <p>This class provides a small, focused API to scroll a given element into
 * view. It uses JavaScript execution to call the browser's native
 * Element.scrollIntoView method. A modern invocation includes options to
 * center the element in the viewport and use a smooth scroll behaviour.
 *
 * <p>Because some older browser driver implementations or remote drivers might
 * not accept the object-form argument to scrollIntoView (options), the
 * implementation falls back to the simpler call without options if the first
 * attempt fails. This keeps the utility robust across driver versions while
 * preferring the nicer UX when available.</p>
 */
public class ScrollUtils {

	/**
	 * Scrolls the provided element into view, attempting a modern invocation
	 * first and falling back to a simpler call if the driver doesn't support
	 * the options object.
	 *
	 * @param driver  the WebDriver used to execute JavaScript
	 * @param element the WebElement to bring into view
	 */
	public static void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Modern usage: provide options to center the element and request
		// a smooth scrolling animation. Note the deliberate spelling of
		// 'behavior' — some legacy examples use 'behaviour' which is incorrect
		// for the DOM API. The original code used 'behaviour' causing the
		// browser to ignore the option; we use the correct 'behavior'.
		String modernScript = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});";

		try {
			// Try the modern API first; for drivers that support this, the UI
			// will smoothly center the element.
			js.executeScript(modernScript, element);
		} catch (Exception e) {
			// If the modern API isn't supported by the underlying driver, fall
			// back to the widely-supported call that takes a boolean. This is
			// more compatible with older or minimal JS execution environments.
			String fallbackScript = "arguments[0].scrollIntoView(true);";
			js.executeScript(fallbackScript, element);
		}
	}

}