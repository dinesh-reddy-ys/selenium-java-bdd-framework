package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.DriverFactory;

/**
 * Hooks class containing Cucumber setup and teardown methods.
 * Manages WebDriver lifecycle for each test scenario.
 */
public class Hooks {

    /**
     * WebDriver instance to be used across the test execution.
     * Static to maintain single instance across hooks.
     */
    private static WebDriver driver;

    /**
     * Setup method that runs before each scenario.
     * Initializes the WebDriver based on specified browser preference.
     * Browser can be specified through system property 'browser',
     * defaults to Chrome if not specified.
     *
     * Usage: mvn test -Dbrowser=firefox
     */
    @Before
    public void setUp() {
        // Retrieve browser preference from system properties with default value "chrome"
        String browser = System.getProperty("browser", "chrome");
        
        // Initialize WebDriver with specified browser
        DriverFactory.initDriver(browser);
    }

    /**
     * Teardown method that runs after each scenario.
     * Captures screenshot for failed scenarios and closes the browser.
     *
     * @param scenario Current test scenario, injected by Cucumber
     */
    @After
    public void tearDown(Scenario scenario) {
        // Get the current driver instance
        driver = DriverFactory.getDriver();
        
        // Capture screenshot if scenario failed
        if (scenario.isFailed() && driver != null) {
            try {
                // Cast driver to TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                
                // Capture screenshot as bytes
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                
                // Attach screenshot to scenario
                scenario.attach(screenshot, "image/png", "Screenshot-" + scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        // Quit WebDriver if it exists
        if (driver != null) {
            DriverFactory.quitDriver(); // Gracefully close the browser after test execution
        }
    }
}