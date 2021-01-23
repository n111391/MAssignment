package testRunner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",glue = {"stepDefs"},plugin = {"pretty", "html:target/cucumber-reports.html"},
dryRun = false, // to check the the mapping is proper between feature file and step def file
stepNotifications = true) //enable step notifications

public class Runner {

	@BeforeClass
	public static void setUp() {
		System.out.println("----------------------------------------This execution is started----------------------------------------");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("----------------------------------------This execution is completed-------------------------------------");
	}
}