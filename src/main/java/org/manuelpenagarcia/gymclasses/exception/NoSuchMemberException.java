package org.manuelpenagarcia.gymclasses.exception;

public class NoSuchMemberException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchMemberException() {
		super("The required member does not exist");
	}
}
