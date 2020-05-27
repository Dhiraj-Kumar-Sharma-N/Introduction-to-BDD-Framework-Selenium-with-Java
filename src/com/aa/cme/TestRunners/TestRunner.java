package com.aa.cme.TestRunners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "./Features",

		glue = { "com.aa.cme.StepDefinitions"},

		tags = "@SmokeTC", strict = true, monochrome = true, plugin = { "pretty",
				"html:Execution_Report/Cucumber-Report", "json:Execution_Report/Cucumber-Report/Report.json",
				"junit:Execution_Report/Cucumber-Report/Report.xml" })

public class TestRunner extends AbstractTestNGCucumberTests {
	  private TestNGCucumberRunner testNGCucumberRunner;
	  
	  @Parameters({"BrowserName","Environment","TestResult"})
	  
	    @BeforeClass(alwaysRun = true)
	    public void setUpClass() throws Exception {    	
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }

	    @Test(dataProvider = "features")    
	    public void feature(PickleEventWrapper eventwrapper,CucumberFeatureWrapper cucumberFeature) throws Throwable {
	    	//testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	    	testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
	    }
	    
	    @DataProvider//(parallel=true)
	    public Object[][] features() {
	       // return testNGCucumberRunner.provideFeatures();    	
	    	 return testNGCucumberRunner.provideScenarios();
	    }
	    
	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() throws Exception {    	
	        testNGCucumberRunner.finish();        
	    }
	}