Feature: CME power app in MS Teams 

#@LoginTest
#Scenario: To verify CME power app login page is displayed with Station, Workgroup and Role dropdown 
#	Given User is logged into MS Teams with Valid Credentials
#	When User click on CME power app icon located in left App Bar
#	Then User is able to see Station, workgroup and role dropdown with Text Labels 


@LoginTest	
Scenario: To Verify all the stations are displayed when user clicks on the station dropdown 
	Given User is logged into MS Teams with Valid Credentials
	When User click on CME power app icon located in left App Bar
	And User clicks on Station Dropdown from the Power App Login Page
	Then User is able to Verify all the stations are displayed
	
	
#Scenario: 
#	To Verify all the Workgroups are displayed when user clicks on the Workgroup dropdown 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see Station, workgroup and role dropdown on the login page 
#	Then User is able to Verify all the Workgroups are displayed when user clicks on the workgroup dropdown 
#	
#	
#Scenario: 
#	To Verify all the Roles are displayed when user clicks on the Workgroup dropdown 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see Station, workgroup and role dropdown on the login page 
#	Then User is able to Verify all the roles are displayed when user clicks on the Role dropdown 
#	
#	
#Scenario: 
#	To verify if user clicks on the submit button after selecting station, WG and roles is being redirected to Teams Enrollment Page 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see Station, workgroup and role dropdown on the login page 
#	Then User is redirected Team Enrollment page when user clicks on the Submit button 
#	
#	
#Scenario: 
#	To verify if the Full name of the User is displayed correctly on the login page on the right side 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see the Full name on the login page on the right side 
#	
#	
#Scenario: To verify if the airplane image is being displayed on the login page 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see the airplane image is being displayed on the login page 
#	
#	
#Scenario: 
#	To verify if the ConnectMe name with symbol is displayed correctly above the airplane image on the login page 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see ConnectMe name with symbol above the airplane image on the login page 
#	
#Scenario: 
#	To verify if the American Airlines along with its symbol is displayed on the right side on the login page 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see American Airlines with symbol on the right side on the login page 
#	
#Scenario: 
#	To Verify if the Privacy Statement is displayed correctly under the submit button on the login page 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see the Privacy Statement under the submit button on the login page 
#	
#	
#Scenario: 
#	To Verify if the View Privacy Statement link is displayed on blue color correctly on the login page under the submit button 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is able to see View Privacy Statement link is displayed on blue color under the submit button on the login page 
#	
#Scenario: 
#	To verify if the user clicks on the View Privacy Statement link on the login page is being redirected to a new webpage 
#	Given User is successfully logged into MS Teams 
#	When User click on CME power app icon located on left side below the activity 
#	Then User is redirected to a new webpage when clicked on privacy statement link on the login page 
#	
