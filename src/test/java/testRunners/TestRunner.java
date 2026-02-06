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
        tags = "Upload"

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
