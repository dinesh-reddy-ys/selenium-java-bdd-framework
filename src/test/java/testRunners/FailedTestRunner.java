package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "@target/failed_scenarios.txt",
        glue = {"stepDefs","hooks"},
        plugin = {
                "pretty"
        },
        monochrome = true
)
public class FailedTestRunner extends AbstractTestNGCucumberTests {
   private static final ThreadLocal<String> browserType = new ThreadLocal<>();


   @Parameters({"browser"})
   @BeforeClass
   public void setupBrowser(@Optional("chrome") String browser){
          browserType.set(browser);
          //log the recieved browser type
          System.out.println("Browser preference: " + browser);
          // Set browser preference for parallel execution
           System.setProperty("browser", browser);
   }
   public static String getBrowserType(){
           return browserType.get();
   }
}
