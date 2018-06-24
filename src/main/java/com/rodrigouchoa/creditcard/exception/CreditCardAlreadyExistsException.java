package com.rodrigouchoa.creditcard.exception;

/**
 * Thrown when trying to save a credit card that already exists.
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
public class CreditCardAlreadyExistsException extends CreditCardException {

	public CreditCardAlreadyExistsException() {
		super();
		
	}

	public CreditCardAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CreditCardAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CreditCardAlreadyExistsException(String message) {
		super(message);
		
	}

	public CreditCardAlreadyExistsException(Throwable cause) {
		super(cause);
		
	}


}
