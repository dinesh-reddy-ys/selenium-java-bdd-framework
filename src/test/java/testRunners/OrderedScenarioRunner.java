package testRunners;

import io.cucumber.java.Before;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderedScenarioRunner {
   private TestNGCucumberRunner runner;

   @BeforeClass(alwaysRun = true)
   public  void setUpClass(){
       runner = new TestNGCucumberRunner(DynamicRunner.class);
   }

    @DataProvider(name = "orderedTags",parallel = false)
    public Object[][] tagsInOrder(){
       return new Object[][]{
               {"@Order001"},
               {"@Order002"}
       };
    }
    @Test(dataProvider = "orderedTags")
    public void runByTag(String tag){
        System.setProperty("cucumber.filter.tags",tag);

        runner = new TestNGCucumberRunner(DynamicRunner.class);
        for(Object[] scenario : runner.provideScenarios()){
           PickleWrapper pickle = (PickleWrapper) scenario[0];
            //FeatureWrapper feature = (FeatureWrapper) scenario[1];

            if(pickle.getPickle().getTags().contains(tag.replace("@",""))){
                runner.runScenario(pickle.getPickle());
            }
        }
            runner.finish();
    }

    @AfterClass(alwaysRun = true)
    public  void  tearDownClass(){
       if(runner != null){
           runner.finish();
       }
    }
}
