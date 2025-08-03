package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import testRunners.ParallelTestRunner;
import utils.DriverFactory;
import utils.ExtentManager;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * Hooks class containing Cucumber setup and teardown methods.
 * Manages WebDriver lifecycle for each test scenario.
 */
public class Hooks {

    /**
     * WebDriver instance to be used across the test execution.
     * Static to maintain single instance across hooks.
     */
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ExtentReports extent = ExtentManager.getInstance();
    public static ExtentTest test;

    /**
     * Setup method that runs before each scenario.
     * Initializes the WebDriver based on specified browser preference.
     * Browser can be specified through system property 'browser',
     * defaults to Chrome if not specified.
     *
     * Usage: mvn test -Dbrowser=firefox
     */

    @Before
    public void setUp(Scenario scenario) {
        // Retrieve browser preference from system properties with default value "chrome"
        String browser = ParallelTestRunner.getBrowserType();
                ;
        if (browser == null || browser.isEmpty()) {
           browser = System.getProperty("browser");
          //  throw new IllegalArgumentException("Browser parameter not set! Please check TestNG configuration.");
        }

        // Initialize driver with specified browser
        driver.set(DriverFactory.initDriver(browser));
        //System.out.println("Starting test execution with " + browser + " browser");
        //test = extent.createTest(scenario.getName());
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
        WebDriver currentDriver = driver.get();

//        // Capture screenshot if scenario failed
//        if (scenario.isFailed() && driver != null) {
//            try {
//                // Cast driver to TakesScreenshot
//                TakesScreenshot ts = (TakesScreenshot) driver;
//
//                // Capture screenshot as bytes
//                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
//
//                // Attach screenshot to scenario
//                scenario.attach(screenshot, "image/png", "Screenshot-" + scenario.getName());
//            } catch (Exception e) {
//                System.err.println("Failed to capture screenshot: " + e.getMessage());
//            }
//        }

        if(scenario.isFailed()) {
        	test.fail("Scenario failed: " + scenario.getName());
        }else {
        	test.pass("Scenario passed: " + scenario.getName());
        }
        extent.flush();
        // Quit WebDriver if it exists
        if (currentDriver != null) {
            DriverFactory.quitDriver(); // Gracefully close the browser after test execution
            driver.remove();
        }
    }
}