package com.aa.cme.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import com.aa.cme.POM.CME_PowAppLoginPage;
import com.aa.cme.POM.MS_LoginPage;
import com.aa.cme.Utilities.ExtentReportListener;
import com.aa.cme.Utilities.Log4jListener;
import com.aa.cme.Utilities.UtilityFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Scenario;
import cucumber.api.java.en.*;

//@RunWith(Cucumber.class)
public class CMEPowAppLoginStepDef extends ExtentReportListener {

	WebDriver driver;
	private MS_LoginPage loginPageObj = new MS_LoginPage();
	private CME_PowAppLoginPage PowAppLogObj = new CME_PowAppLoginPage();
	public ExtentTest Scenario = null;
	public ExtentTest Test = null;
	String str = "Exception";
	String SceneName;
	String Uname;
	String Pwd;
	String LogMessage;
	Logger log = Log4jListener.getLogger(CMEPowAppLoginStepDef.class);
	public String FeatureName = "MS Teams Login Page Test Cases";
	String MSG = "Testing Demo";

	@Given("^User is logged into MS Teams with Valid Credentials$")
	public void user_is_logged_into_ms_teams_with_valid_credentials() throws Throwable {
		try {
			Thread.sleep(2000);
			Uname = UtilityFunctions.readConfigurationFile("ValidUserName");
			Pwd = UtilityFunctions.readConfigurationFile("ValidPassword");
			if (loginPageObj.login(Uname, Pwd) == true) {
				SceneName = Hooks.scenario;
				LogMessage = "User Navigated to MSTeams Page Successfully";
				Scenario = Feature.createNode(Scenario.class, SceneName);
				Test = Scenario.createNode(new GherkinKeyword("Given"),
						"user_is_logged_into_ms_teams_with_valid_credentials");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Login to MSTeams Page";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@When("^User click on CME power app icon located in left App Bar$")
	public void user_click_on_cme_power_app_icon_located_in_left_app_bar() throws Throwable {
		try {
			if (PowAppLogObj.ClickPowerApp() == true) {
				LogMessage = "Power App clicked Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"),
						"user_click_on_cme_power_app_icon_located_in_left_app_bar");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to click on Power App Icon";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("User is able to see Station, workgroup and role dropdown with Text Labels")
	public void user_is_able_to_see_station_workgroup_and_role_dropdown_with_text_labels() throws Throwable {
		try {
			if (PowAppLogObj.ValSTA_WG_ROLE() == true) {
				LogMessage = "Validated Station,Workgroup and Role Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"),
						"user_is_able_to_see_station_workgroup_and_role_dropdown_with_text_labels");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Validate Station, Workgroup and Role ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^User clicks on Station Dropdown from the Power App Login Page$")
	public void user_clicks_on_station_dropdown_from_the_power_app_login_page() throws Throwable {
		try {
			if (PowAppLogObj.ClickStnDrpDwn() == true) {
				LogMessage = "Station Drop Down on PowerApp Page Clicked Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"),
						"user_clicks_on_station_dropdown_from_the_power_app_login_page");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Click  Station Drop Down on PowerApp Page ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^User is able to Verify all the stations are displayed$")
	public void user_is_able_to_verify_all_the_stations_are_displayed() throws Throwable {
		try {
			if (PowAppLogObj.ValStationList() == true) {
				LogMessage = "Validated Station List Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"),
						"user_is_able_to_verify_all_the_stations_are_displayed");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Validate Stations List ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	// ============================ >>> HAPPY PATH <<<
	// =======================================

	@When("^User clicks on Launch Teams button$")
	public void user_clicks_on_launch_teams_button() throws Throwable {
		try {
			if (PowAppLogObj.ClickLaunchBtn() == true) {
				LogMessage = "LaunchTeam Button Clicked Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"), "user_clicks_on_launch_teams_button");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Click LaunchTeam Button ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}

	}

	@Then("^User gets redirected to Team Enrollment page with List of Avaliable Flights$")
	public void user_gets_redirected_to_team_enrollment_page_with_list_of_avaliable_flights() throws Throwable {
		try {
			if (PowAppLogObj.ValENRPage() == true) {
				LogMessage = "Redirected to Enrollment Page Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"),
						"user_gets_redirected_to_team_enrollment_page_with_list_of_avaliable_flights");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Redirect to Enrollment Page ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^User navigates to CME Dashboard Page$")
	public void user_navigates_to_cme_dashboard_page() throws Throwable {
		try {
			if (PowAppLogObj.ValCMEDashboard() == true) {
				LogMessage = "CME Dashboard Page is Displayed Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "user_navigates_to_cme_dashboard_page");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to display CME Dashboard Page ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^Selects Desired Station, Workgroup and role$")
	public void selects_desired_station_workgroup_and_role() throws Throwable {
		try {
			if (PowAppLogObj.SelectStaWGRole() == true) {
				LogMessage = "Desrired Station,WG & Role Selected Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"), "selects_desired_station_workgroup_and_role");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Select Station,WG & Role ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^clicks on Submit$")
	public void clicks_on_submit() throws Throwable {
		try {
			if (PowAppLogObj.ClickSubmitBtn() == true) {
				LogMessage = "Submit Button Clicked Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"), "clicks_on_submit");
				Test.pass(LogMessage);
				log.info(LogMessage);
				Thread.sleep(3000);
			} else {
				LogMessage = "Failed to Click Submit Button ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^User clicks on teams Icon$")
	public void user_clicks_on_teams_tab() throws Throwable {
		try {
			if (PowAppLogObj.ClickTeamsIcon() == true) {
				LogMessage = "Teams Icon Clicked Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"), "user_clicks_on_teams_tab");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Click Teams Icon ";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@When("^User clicks on “General” Topic of Station Comminucation Channel$")
	public void user_clicks_on_general_topic_of_station_comminucation_channel() throws Throwable {
		try {
			if (PowAppLogObj.ClickStaChannel() == true) {
				LogMessage = " General Topic of Station Comminucation Channel Clicked Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"),
						"user_clicks_on_general_topic_of_station_comminucation_channel");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Click “General” Topic for Station Comminucation Channel";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^Sent Message is displayed in the Chat Panel$")
	public void sent_message_is_displayed_in_the_chat_panel() throws Throwable {
		try {
			if (PowAppLogObj.ValLastSendMsg(MSG) == true) {
				LogMessage = " Sent Message Verified Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "sent_message_is_displayed_in_the_chat_panel");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Verify Sent Message";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^User sends a Test message$")
	public void user_sends_a_test_message() throws Throwable {
		try {
			if (PowAppLogObj.randomsendMessage(MSG) == true) {
				LogMessage = " Random Message Send Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"), "user_sends_a_test_message");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to send Random Message";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

/*
	@When("^User SignOut from the application$")
	public void User_signout_from_the_application() throws Throwable {
		try {
			if (loginPageObj.SignOut() == true) {
				LogMessage = "User Signed Out Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"), "User_signout_from_the_application");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Sign Out";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^Signed Out Successfully$")
	public void Signed_out_successfully() throws Throwable {
		try {
			if (loginPageObj.ValSignOut() == true) {
				LogMessage = "User Signed Out Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "Signed_out_successfully");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Sign Out";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}
	
*/
}
