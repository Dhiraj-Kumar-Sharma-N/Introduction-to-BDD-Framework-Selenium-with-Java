package com.aa.cme.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import com.aa.cme.POM.MS_LoginPage;
import com.aa.cme.Utilities.ExtentReportListener;
import com.aa.cme.Utilities.Log4jListener;
import com.aa.cme.Utilities.UtilityFunctions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Scenario;
import cucumber.api.java.en.*;

//@RunWith(Cucumber.class)
public class MSLoginStepDef extends ExtentReportListener {

	WebDriver driver;
	private MS_LoginPage loginPageObj = new MS_LoginPage();
	public ExtentTest Scenario = null;
	public ExtentTest Test = null;
	String str = "Exception";
	String SceneName;
	String LogMessage;
	Logger log = Log4jListener.getLogger(MSLoginStepDef.class);
	public String FeatureName = "MS Teams Login Page Test Cases";

	@Given("^User Navigate to MSTeams login page$")
	public void User_navigate_to_msteams_login_page() throws Throwable {
		try {
			if (loginPageObj.ValLoginPage() == true) {
				SceneName = Hooks.scenario;
				LogMessage = "User Navigated to MSTeams Page Successfully";
				// test = extent.createTest(Feature.class, "MS Teams Login Page Test Cases");
				Scenario = Feature.createNode(Scenario.class, SceneName);
				Test = Scenario.createNode(new GherkinKeyword("Given"), "User_navigate_to_msteams_login_page");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Navigated to MSTeams Page";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@When("^User Enter a Valid User Name & Click Next$")
	public void User_enter_a_valid_user_name_click_next() throws Throwable {
		try {
			if (loginPageObj.EnterUN(UtilityFunctions.readConfigurationFile("ValidUserName")) == true) {
				LogMessage = "Valid User Name Entered Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"), "User_enter_a_valid_user_name_click_next");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Enter Valid User Name";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^User Enter InValid Password & Click SignIn$")
	public void User_enter_invalid_password_click_next() throws Throwable {
		try {
			if (loginPageObj.EnterPwd(UtilityFunctions.readConfigurationFile("InvalidPassword")) == true) {
				LogMessage = "Invalid Password Entered Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"), "User_enter_invalid_password_click_next");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Enter Invalid Password";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^Incorrect Password Error Message is Displayed$")
	public void Incorrect_password_error_message_is_displayed() throws Throwable {
		String str2 = "Incorrect user ID or password. Type the correct user ID and password, and try again.";
		try {
			if (loginPageObj.ValErrMsg(str2, "INVALIDPWDTXT") == true) {
				LogMessage = "Incorrect Password Error Message is Displayed Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "Incorrect_password_error_message_is_displayed");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Display Incorrect Password Error Message";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@When("^User Enter a InCorrect User Name & Click Next$")
	public void User_enter_a_incorrect_user_name_click_next() throws Throwable {
		try {
			if (loginPageObj.EnterUN(UtilityFunctions.readConfigurationFile("IncorrectUserName")) == true) {
				LogMessage = "Incorrect User Name Entered Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"), "User_enter_a_incorrect_user_name_click_next");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Enter Incorrect UserName";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^Incorrect UserName Error Message is Displayed$")
	public void Incorrect_username_error_message_is_displayed() throws Throwable {
		String str2 = "This username may be incorrect. Enter a different one or create a new ne.";
		try {
			if (loginPageObj.ValErrMsg(str2, "INCORRECTUNTXT") == true) {
				LogMessage = "Incorrect UserName Error Message is Displayed Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "Incorrect_username_error_message_is_displayed");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Display Incorrect UserName Error Message";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@When("^User Enter InValid User Name & Click Next$")
	public void User_enter_invalid_user_name_click_next() throws Throwable {
		try {
			if (loginPageObj.EnterUN(UtilityFunctions.readConfigurationFile("InvalidUserName")) == true) {
				LogMessage = "Invalid User Name Entered successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"), "User_enter_invalid_user_name_click_next");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Enter Invalid UserName";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^Invalid UserName Error Message is Displayed$")
	public void Invalid_username_error_message_is_displayed() throws Throwable {
		String str2 = "We couldn't find an account with that username. Try another, or get a new Microsoft account.";
		try {
			if (loginPageObj.ValErrMsg(str2, "INCORRECTUNTXT") == true) {
				LogMessage = "Incorrect UserName Error Message is Displayed Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "Invalid_username_error_message_is_displayed");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Display Incorrect UserName Error Message";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@And("^User Enter Valid Password & Click SignIn$")
	public void User_enter_valid_password_click_signin() throws Throwable {
		try {
			if (loginPageObj.EnterPwd(UtilityFunctions.readConfigurationFile("ValidPassword")) == true) {
				LogMessage = "Valid Password Entered Successfully";
				Test = Scenario.createNode(new GherkinKeyword("And"), "User_enter_valid_password_click_signin");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Enter Valid Password";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@When("^On selection WebApp link on AppPromotion Page$")
	public void On_selection_webapp_link_on_apppromotion_page() throws Throwable {
		try {
			if (loginPageObj.SelectWebApp() == true) {
				LogMessage = "Web Application Link selected Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"), "On_selection_webapp_link_on_apppromotion_page");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to select Web App Link";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

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

	@Then("^AppPromotion Page is Displayed$")
	public void APP_promotion_page_is_displayed() throws Throwable {
		try {
			if (loginPageObj.ValAppPromoPage() == true) {
				LogMessage = "Application Promotion Page is Displayed Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "APP_promotion_page_is_displayed");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Display APP Promotion Page";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^Signed In Successfully$")
	public void Signed_in_successfully() throws Throwable {
		try {
			if (loginPageObj.ValSignIn() == true) {
				LogMessage = "User Signed In Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "Signed_in_successfully");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to Sign In";
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

	@When("^User Clears the InvalidUserName and Enters Exception UserName and Clicks on Next$")
	public void User_clears_the_invalidusername_and_enters_exception_username_and_clicks_on_next() throws Throwable {
		try {
			if (loginPageObj.EnterUN(UtilityFunctions.readConfigurationFile("InvalidPhoneNumUN")) == true) {
				LogMessage = "User Clears and Re-Enters Peculiar UserName Successfully";
				Test = Scenario.createNode(new GherkinKeyword("When"),
						"User_clears_the_invalidusername_and_enters_exception_username_and_clicks_on_next");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to clear and Re-Enter Peculiar UserName";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

	@Then("^InvalidPhoneNo Error Message is Displayed$")
	public void Invalidphoneno_error_msg_is_displayed() throws Throwable {
		String str2 = "The phone number you entered isn't valid. Your phone number can contain numbers, spaces, and these special characters: ( ) [ ] . - # * /";
		try {
			if (loginPageObj.ValErrMsg(str2, "INCORRECTUNTXT") == true) {
				LogMessage = "Incorrect PhoneNumber/User name Error Message is Displayed Successfully";
				Test = Scenario.createNode(new GherkinKeyword("Then"), "Invalidphoneno_error_msg_is_displayed");
				Test.pass(LogMessage);
				log.info(LogMessage);
			} else {
				LogMessage = "Failed to display Incorrect PhoneNumber/Username Error Message";
				failStep(Test, LogMessage);
				log.info(LogMessage);
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", UtilityFunctions.driver, Test, e);
		}
	}

}
