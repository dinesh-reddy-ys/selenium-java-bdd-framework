package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs","hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/reports.html",
                "rerun:target/failed_scenarios.txt"
        },
        tags = "@Login",
        // This tag can be used to run specific scenarios
        monochrome = true // Makes the console output more readable
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
