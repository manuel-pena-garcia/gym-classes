package org.manuelpenagarcia.gymclasses.exception;

public class FullActivityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FullActivityException() {
		super("The selected activity is already full, no more members are allowed to sign themselves in");
	}
}
