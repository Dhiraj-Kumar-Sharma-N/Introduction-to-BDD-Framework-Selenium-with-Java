$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./Features/MSLogin.feature");
formatter.feature({
  "name": "MS Teams Login Page Test Cases",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Verify Incorrect UserName Error Message is Displayed when User Logs in with Incorrect UserName",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SmokeTC"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User Navigate to MSTeams login page",
  "keyword": "Given "
});
formatter.match({
  "location": "MSLoginStepDef.User_navigate_to_msteams_login_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter a InCorrect User Name \u0026 Click Next",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.User_enter_a_incorrect_user_name_click_next()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Incorrect UserName Error Message is Displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.Incorrect_username_error_message_is_displayed()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify InvalidUserName Error/InvalidPhoneNo Msg is Displayed when User Logs in with Invalid UserName",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SmokeTC"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User Navigate to MSTeams login page",
  "keyword": "Given "
});
formatter.match({
  "location": "MSLoginStepDef.User_navigate_to_msteams_login_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter InValid User Name \u0026 Click Next",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.User_enter_invalid_user_name_click_next()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Invalid UserName Error Message is Displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.Invalid_username_error_message_is_displayed()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Clears the InvalidUserName and Enters Exception UserName and Clicks on Next",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.User_clears_the_invalidusername_and_enters_exception_username_and_clicks_on_next()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "InvalidPhoneNo Error Message is Displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.Invalidphoneno_error_msg_is_displayed()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify Incorrect Password Error Message is Displayed when User Logs in with Valid UserName and Invalid Password",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SmokeTC"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User Navigate to MSTeams login page",
  "keyword": "Given "
});
formatter.match({
  "location": "MSLoginStepDef.User_navigate_to_msteams_login_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter a Valid User Name \u0026 Click Next",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.User_enter_a_valid_user_name_click_next()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter InValid Password \u0026 Click SignIn",
  "keyword": "And "
});
formatter.match({
  "location": "MSLoginStepDef.User_enter_invalid_password_click_next()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Incorrect Password Error Message is Displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.Incorrect_password_error_message_is_displayed()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify User is able to Login and Log out to MS Teams with valid UserName and Valid Password",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SmokeTC"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User Navigate to MSTeams login page",
  "keyword": "Given "
});
formatter.match({
  "location": "MSLoginStepDef.User_navigate_to_msteams_login_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter a Valid User Name \u0026 Click Next",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.User_enter_a_valid_user_name_click_next()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter Valid Password \u0026 Click SignIn",
  "keyword": "And "
});
formatter.match({
  "location": "MSLoginStepDef.User_enter_valid_password_click_signin()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "AppPromotion Page is Displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.APP_promotion_page_is_displayed()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "On selection WebApp link on AppPromotion Page",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.On_selection_webapp_link_on_apppromotion_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Signed In Successfully",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.Signed_in_successfully()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User SignOut from the application",
  "keyword": "When "
});
formatter.match({
  "location": "MSLoginStepDef.User_signout_from_the_application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Signed Out Successfully",
  "keyword": "Then "
});
formatter.match({
  "location": "MSLoginStepDef.Signed_out_successfully()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});