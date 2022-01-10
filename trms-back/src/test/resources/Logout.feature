Feature: User logs out

Background: Given the user is already logged in

Scenario: the user is logging out
	Given the user is already logged in
	When the user is on his profile
	Then the user exits his profile