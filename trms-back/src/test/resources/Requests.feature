Feature: Requests by the user table works

Background: Given the user is on the his profile

Scenario: requests created by user are in the table
	Given the user is on his profile
	When the requests are loaded
	Then the table has his requests in it
	