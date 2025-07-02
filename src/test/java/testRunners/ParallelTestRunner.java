package testRunners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs","hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/reports.html",
        "json:target/cucumber-reports/report.json"},
        tags = "@Search"  // This tag can be used to run specific scenarios
)
public class ParallelTestRunner extends AbstractTestNGCucumberTests {
   private static final ThreadLocal<String> browserType = new ThreadLocal<>();

    @Parameters({"browser"})
    @BeforeClass
    public  void setupBrowser(@Optional("chrome") String browser){

       browserType.set(browser);
        // Log the received browser preference
        System.out.println("Browser preference: " + browser);
        // Set browser preference for parallel execution
        System.setProperty("browser", browser);
    }
    public static String getBrowserType(){
        return browserType.get();
    }
}
