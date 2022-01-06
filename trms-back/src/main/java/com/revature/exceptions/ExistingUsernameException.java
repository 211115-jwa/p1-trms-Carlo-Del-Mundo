package com.revature.exceptions;

public class ExistingUsernameException extends Exception{
	/**
	 * auto-generated serial version UID.
	 */
	private static final long serialVersionUID = -3797750929482373641L;

	public ExistingUsernameException() {
		super("This username is already taken by someone.");
	}
}
