package com.runnertests;
//tags= "@DeletePlace"
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features="src/test/java/com/features",plugin="json:target/jsonReports/cucumber-report.json",glue= {"com.stepdefinitions"})
public class RunnerTest extends AbstractTestNGCucumberTests{
	//,tags= "@DeletePlace"
	//,tags= "@AddPlace"
}
