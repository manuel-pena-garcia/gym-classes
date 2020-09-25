package org.manuelpenagarcia.gymclasses.exception;

public class NoSuchActivityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchActivityException() {
		super("The required activity does not exist");
	}
}
