package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Factory class for managing WebDriver instances in a thread-safe manner.
 * Supports multiple browser types and handles driver lifecycle.
 */
public class DriverFactory {
	/**
	 * ThreadLocal variable to store WebDriver instances. Ensures thread safety when
	 * running tests in parallel.
	 */
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * Initializes a new WebDriver instance based on the specified browser type.
	 *
	 * @param browser The browser type to initialize ("chrome", "firefox", or
	 *                "edge")
	 * @return WebDriver instance for the specified browser
	 * @throws IllegalArgumentException if an unsupported browser type is specified
	 */
	public static WebDriver initDriver(String browser) {
		System.out.println("DriverFactory received browser parameter: " + browser);

		// Convert browser parameter to lowercase to ensure case-insensitive comparison
		switch (browser.toLowerCase()) {
		case "chrome":
			// Set up ChromeDriver using WebDriverManager
			WebDriverManager.chromedriver().setup();

			// Set custom download directory for chrome
			String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadPath);
			prefs.put("download.prompt_for_download", false);
			prefs.put("safebrowsing.enabled", true);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			System.out.println("Download path : " + downloadPath);
			System.out.println("Exists: " + new File(downloadPath).exists());
			driver.set(new ChromeDriver(options));
			break;
		case "firefox":
			// Set up FirefoxDriver using WebDriverManager
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
		case "edge":
			// Set up EdgeDriver using WebDriverManager
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;
		case "ie":
			// Set up InternetExplorerDriver using WebDriverManager
			WebDriverManager.iedriver().setup();
			driver.set(new EdgeDriver());
			break;
		case "safari":
			// Set up SafariDriver using WebDriverManager
			// WebDriverManager.safaridriver().setup();
			driver.set(new SafariDriver());
			break;
		default:
			// Throw exception for unsupported browser types
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		// Maximize the browser window after initialization
		getDriver().manage().window().maximize();
		return getDriver();
	}

	/**
	 * Retrieves the WebDriver instance associated with the current thread.
	 *
	 * @return WebDriver instance for the current thread
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}

	/**
	 * Quits the WebDriver instance and removes it from ThreadLocal storage. Should
	 * be called after test execution to clean up resources.
	 */
	public static void quitDriver() {
		if (getDriver() != null) {
			getDriver().quit(); // Close the browser and end the session
			driver.remove(); // Remove the ThreadLocal instance
		}
	}
}