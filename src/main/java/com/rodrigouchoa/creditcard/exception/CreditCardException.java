package com.rodrigouchoa.creditcard.exception;

/**
 * General purpose business exception. 
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
public class CreditCardException extends RuntimeException {

	public CreditCardException() {
		super();
		
	}

	public CreditCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CreditCardException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CreditCardException(String message) {
		super(message);
		
	}

	public CreditCardException(Throwable cause) {
		super(cause);
		
	}

}
