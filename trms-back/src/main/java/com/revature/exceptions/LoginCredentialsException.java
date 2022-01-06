package com.revature.exceptions;

public class LoginCredentialsException extends Exception{
	/**
	 * auto-generated serial version UID.
	 */
	private static final long serialVersionUID = -4448695898268410712L;

	public LoginCredentialsException() {
		super("Username or password is incorrect.");
	}
}
