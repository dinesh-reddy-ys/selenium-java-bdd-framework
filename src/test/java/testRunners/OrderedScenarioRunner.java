package testRunners;

import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderedScenarioRunner {
	private TestNGCucumberRunner runner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		runner = new TestNGCucumberRunner(DynamicRunner.class);
	}

	@DataProvider(name = "orderedTags", parallel = false)
	public Object[][] tagsInOrder() {
		return new Object[][] { { "@Order001" }, { "@Order002" } };
	}

	@Test(dataProvider = "orderedTags")
	public void runByTag(String tag) {
		System.setProperty("cucumber.filter.tags", tag);

		//runner = new TestNGCucumberRunner(DynamicRunner.class);
		for (Object[] scenario : runner.provideScenarios()) {
			PickleWrapper pickle = (PickleWrapper) scenario[0];

			// Ensure scenario has tags and matches the specified tag
			if (pickle.getPickle().getTags() != null && !pickle.getPickle().getTags().isEmpty()
					&& pickle.getPickle().getTags().contains(tag.replace("@", ""))) {
				System.out.println("Executing scenario: " + pickle.getPickle().getName());
				runner.runScenario(pickle.getPickle());
			} else {
				System.out.println("Skipping scenario: " + pickle.getPickle().getName());
			}
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (runner != null) {
			runner.finish();
		}
	}
}
