package com.rodrigouchoa.creditcard.util;

import java.io.Serializable;

/**
 * Just an object to represent the errors.
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
public class ErrorDTO implements Serializable {
	private String message;
	private Integer httpCode;
	

	public ErrorDTO(Integer httpCode, String message) {
		Utils utils = new Utils();
		utils.assertNotNull(httpCode);
		utils.assertNotNullOrEmpty(message);
		
		this.httpCode = httpCode;
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

}
