package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set default browser as Chrome, or use the system property if provided.
       // String browser = System.getProperty("browser", "chrome").toLowerCase();
       String browser = "chrome";
        try {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup(); // Sets up ChromeDriver
                    driver = new ChromeDriver(); // Initializes ChromeDriver
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup(); // Sets up FirefoxDriver
                    driver = new FirefoxDriver(); // Initializes FirefoxDriver
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Maximize the browser window and clear cookies
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver for browser: " + browser, e);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Gracefully close the browser after the test execution
        }
    }

    public static WebDriver getDriver() {
        return driver; // Provide WebDriver instance to be accessed in other test classes
    }
}