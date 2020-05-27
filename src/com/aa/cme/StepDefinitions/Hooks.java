package com.aa.cme.StepDefinitions;

import com.aa.cme.Utilities.UtilityFunctions;

import cucumber.api.Result.Type;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	private UtilityFunctions utilityFunction = new UtilityFunctions();
	MSLoginStepDef StepDefObj = new MSLoginStepDef();
	public static String scenario;
	public static String featureName;
	
	@Before
	public Object beforeScenario(Scenario s) {
		scenario=s.getName();
		String BrowserName = UtilityFunctions.readConfigurationFile("BrowserName");
		utilityFunction.initilizeDriver(BrowserName);
		return scenario;	
	}

	@After
	public void AfterScenario(Scenario s) {
		String str ="ResultOutputPath";
		String FName = StepDefObj.FeatureName;
		Type ScenarioStaus = s.getStatus();
		utilityFunction.afterMethod(str,scenario,FName,ScenarioStaus);
		utilityFunction.closure();
		
	}
}
