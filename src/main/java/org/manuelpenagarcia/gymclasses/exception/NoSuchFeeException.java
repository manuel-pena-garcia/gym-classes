package org.manuelpenagarcia.gymclasses.exception;

public class NoSuchFeeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchFeeException() {
		super("The required fee does not exist");
	}
}
