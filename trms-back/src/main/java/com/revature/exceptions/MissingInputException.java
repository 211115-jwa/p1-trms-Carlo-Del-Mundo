package com.revature.exceptions;

public class MissingInputException extends Exception{
	/**
	 * auto-generated serial version UID.
	 */
	private static final long serialVersionUID = 455402155537716404L;

	public MissingInputException(String errorMessage) {
		super(errorMessage);
	}
}
