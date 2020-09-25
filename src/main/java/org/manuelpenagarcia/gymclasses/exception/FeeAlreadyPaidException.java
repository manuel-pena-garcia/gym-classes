package org.manuelpenagarcia.gymclasses.exception;

public class FeeAlreadyPaidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FeeAlreadyPaidException() {
		super("The fee is already paid");
	}

}
