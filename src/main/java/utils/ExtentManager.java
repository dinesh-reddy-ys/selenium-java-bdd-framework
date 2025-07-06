package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    // Static instance of ExtentReports to ensure a single instance is used throughout the application
    private static ExtentReports extent;

    /**
     * Returns the singleton instance of ExtentReports.
     * If the instance is null, it initializes the ExtentReports object with the required configuration.
     *
     * @return ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Define the path for the HTML report
            String reportPath = System.getProperty("user.dir") + "/test-output/extent-reports/ExtentReport.html";

            // Create an ExtentSparkReporter to generate the HTML report
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setOfflineMode(true);// Enable offline mode to prevent network calls
            // Initialize the ExtentReports object
            extent = new ExtentReports();

            // Attach the reporter to the ExtentReports instance
            extent.attachReporter(reporter);

            // Add system information to the report
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "Dinesh Y S");
        }
        return extent;
    }
    
    /**
     * createTest method to create a new test in the ExtentReports instance.
     */
    
    public static ExtentTest createTest(String testName) {
		return extent.createTest(testName);
    }
}
