package com.rodrigouchoa.creditcard.exception;

/**
 * Thrown when the cc number is not valid
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
public class CreditCardNumberInvalidException extends CreditCardException {

	public CreditCardNumberInvalidException() {
	}

	public CreditCardNumberInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public CreditCardNumberInvalidException(String message, Throwable cause) {
		super(message, cause);

	}

	public CreditCardNumberInvalidException(String message) {
		super(message);

	}

	public CreditCardNumberInvalidException(Throwable cause) {
		super(cause);

	}

}
