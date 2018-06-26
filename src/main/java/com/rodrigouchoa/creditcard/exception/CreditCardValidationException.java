package com.rodrigouchoa.creditcard.exception;

/**
 * Thrown when the cc number is not valid
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
public class CreditCardValidationException extends CreditCardException {

	public CreditCardValidationException() {
	}

	public CreditCardValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public CreditCardValidationException(String message, Throwable cause) {
		super(message, cause);

	}

	public CreditCardValidationException(String message) {
		super(message);

	}

	public CreditCardValidationException(Throwable cause) {
		super(cause);

	}

}
