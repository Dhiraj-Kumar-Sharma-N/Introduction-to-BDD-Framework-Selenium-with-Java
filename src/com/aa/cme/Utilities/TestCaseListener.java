package com.aa.cme.Utilities;

/******************************************************************************************************************************************************
 * 
 * Short Description ::This script will execute for reporting function
 * 
 * ****************************************************************************************************************************************************/


import java.util.ArrayList;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aa.cme.StepDefinitions.MSLoginStepDef;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.gherkin.model.Feature;

public class TestCaseListener extends ExtentReportListener implements ITestListener {
	
	public static final ArrayList<ArrayList<String>> reports = new ArrayList<>();
	public static final String reportSheet = "sheet1";
	private static ExtentReports extent;
	MSLoginStepDef StepDefObj = new MSLoginStepDef();

	
	@Override
	public void onFinish(ITestContext arg0) {
		extent.flush();
	}

	@Override
	public void onStart(ITestContext arg0) {
		extent=setUp();
		Feature = extent.createTest(Feature.class, StepDefObj.FeatureName);
		CreateExcelReport();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("Exection Started");

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
