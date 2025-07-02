package testRunners;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/Dynamic.feature:26", // Specify the feature file to run
		glue = {"stepDefs", "hooks"},
		plugin = {"pretty", "html:target/cucumber-reports"},
		tags = "@tag1"  // This tag can be used to run specific scenarios
		// Adjust the tag as needed for your scenarios
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests{
 private static final ThreadLocal<String> browserType = new ThreadLocal<>();
	
 @Parameters({"browser"})
 @BeforeClass
 public static void setupBrowser(@Optional("chrome") String browser) {
	 browserType.set(browser);
 }
}
