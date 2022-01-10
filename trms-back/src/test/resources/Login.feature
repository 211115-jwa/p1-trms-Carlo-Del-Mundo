Feature: user log in

Scenario Outline: logging in with correct credentials
	Given the user is on TRMS home page
	And the user clicks the Login button
	When the user inputs "<username>" and "<password>" to log in
	And the user clicks the login button
	Then the header displays "<fullname>"
	
	Examples:
		|		username									|		password		|		fullname				|
		|		thesupervisor@email.com		|		thesupman8	|		Thesup Ervisor	|
		|		mannydier@email.com				|		diermanny		|		Manny Dyer			|
		
Scenario Outline: user log in using incorrect credentials
	Given the user is on TRMS home page
	And the user clicks the Login button
	When the user inputs "<username>" and "<password>" to log in
	And the user clicks the login button
	Then the page says Username or password is incorrect
	
	Examples:
		|		username				|		password		|
		|		thesupervisor		|		thesupman8	|
		|		mannydier				|		diermanny		|