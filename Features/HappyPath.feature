Feature: MS Teams Happy Path Automation Demo 
		
		@HappyPathTC 
		Scenario: Validate Message Sent by the User is displayed in Chat Panel.
		 
			Given User is logged into MS Teams with Valid Credentials 
			When User click on CME power app icon located in left App Bar 
			And Selects Desired Station, Workgroup and role
			And clicks on Submit
			Then User gets redirected to Team Enrollment page with List of Avaliable Flights
			When User clicks on Launch Teams button
			And User clicks on teams Icon
			Then User navigates to CME Dashboard Page
			When User clicks on “General” Topic of Station Comminucation Channel
			And User sends a Test message
			Then Sent Message is displayed in the Chat Panel
			
