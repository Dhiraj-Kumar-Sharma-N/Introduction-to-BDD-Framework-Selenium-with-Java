		Feature: MS Teams Login Page Test Cases 
		
		@SmokeTC 
		Scenario: Verify Incorrect UserName Error Message is Displayed when User Logs in with Incorrect UserName 
			Given User Navigate to MSTeams login page 
			When User Enter a InCorrect User Name & Click Next 
			Then Incorrect UserName Error Message is Displayed 
			
		@SmokeTC 
		Scenario: Verify InvalidUserName Error/InvalidPhoneNo Msg is Displayed when User Logs in with Invalid UserName 
			Given User Navigate to MSTeams login page 
			When User Enter InValid User Name & Click Next 
			Then Invalid UserName Error Message is Displayed 
			When User Clears the InvalidUserName and Enters Exception UserName and Clicks on Next 
			Then InvalidPhoneNo Error Message is Displayed 
				
		@SmokeTC 
		Scenario: Verify Incorrect Password Error Message is Displayed when User Logs in with Valid UserName and Invalid Password 
			Given User Navigate to MSTeams login page 
			When User Enter a Valid User Name & Click Next 
			And  User Enter InValid Password & Click SignIn 
			Then Incorrect Password Error Message is Displayed 
		
		@SmokeTC 
		Scenario: Verify User is able to Login and Log out to MS Teams with valid UserName and Valid Password 
			Given User Navigate to MSTeams login page 
			When User Enter a Valid User Name & Click Next 
			And  User Enter Valid Password & Click SignIn 
			Then AppPromotion Page is Displayed 
			When On selection WebApp link on AppPromotion Page 
			Then Signed In Successfully 
			When User SignOut from the application 
			Then Signed Out Successfully 
	  