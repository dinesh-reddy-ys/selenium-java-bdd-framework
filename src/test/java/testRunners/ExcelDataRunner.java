package testRunners;

import dataprovider.ExcelDataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs","hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports/reports.html",
                "json:target/cucumber-reports/report.json"}
)
public class ExcelDataRunner extends AbstractTestNGCucumberTests {
  @Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class)
  public  void  runScenario(String username, String password){
      System.setProperty("username",username);
      System.setProperty("password",password);

  }
}
