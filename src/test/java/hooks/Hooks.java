package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import testRunners.ParallelTestRunner;
import utils.BaseUtils;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.OllamaClient;

import java.io.IOException;

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
        String browser = ParallelTestRunner.getBrowserType();
                ;
        if (browser == null || browser.isEmpty()) {
           browser = System.getProperty("browser");
        }
        if(browser == null || browser.isEmpty()){
            browser = "chrome";
        }
        driver.set(DriverFactory.initDriver(browser));
        test = extent.createTest(scenario.getName());

    }

    /**
     * Teardown method that runs after each scenario.
     * Captures screenshot for failed scenarios and closes the browser.
     *
     * @param scenario Current test scenario, injected by Cucumber
     * @throws IOException 
     */
    @After
    public void tearDown(Scenario scenario) throws IOException {
        WebDriver currentDriver = driver.get();
        try {
            if (scenario.isFailed()) {

                test.fail("Scenario failed: "
                        + scenario.getName());

                // Screenshot
                String screenshotPath =
                        BaseUtils.takeScreenshot(
                                scenario.getName());

                test.addScreenCaptureFromPath(
                        screenshotPath);

                // Ollama Analysis
                String aiAnalysis =
                        OllamaClient.analyzeFailure(
                                scenario.getName(),
                                "Scenario execution failed");

                test.info("AI Analysis:\n"
                        + aiAnalysis);

            } else {
                test.pass("Scenario passed: "
                        + scenario.getName());
            }

        } finally {

            extent.flush();

            if (currentDriver != null) {
                DriverFactory.quitDriver();
                driver.remove();
            }
        }
    }
}