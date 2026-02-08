package testRunners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs","hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/reports.html",
                "rerun:target/failed_scenarios.txt"
        },
        //tags ="@Form1",
        // This tag can be used to run specific scenarios
        monochrome = true // Makes the console output more readable
)
public class ParallelTestRunner extends AbstractTestNGCucumberTests {
   private static final ThreadLocal<String> browserType = new ThreadLocal<>();
    public ParallelTestRunner() {
        // browser from XML is already injected by TestNG at this point
        String browser = System.getProperty("browser");
        System.out.println("Runner constructor browser = " + browser);
    }
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
