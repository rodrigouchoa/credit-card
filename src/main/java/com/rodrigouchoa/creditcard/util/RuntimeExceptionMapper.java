package com.rodrigouchoa.creditcard.util;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rodrigouchoa.creditcard.exception.CreditCardAlreadyExistsException;
import com.rodrigouchoa.creditcard.exception.CreditCardNumberInvalidException;

public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
	Logger logger = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

	@Override
	public Response toResponse(RuntimeException exception) {
		/* Leaving this here for now but maybe it should done someplace else,
		 * since not all exceptions here will be an error */
		logger.error(exception.getMessage(), exception);
		
		Integer httpCode = 500;
		if (exception instanceof CreditCardAlreadyExistsException) {
			httpCode = 409;
		}
		if (exception instanceof CreditCardNumberInvalidException) {
			httpCode = 422;
		}
		
		String message = exception.getMessage();
		String detail = stackTraceToString(exception); 
		
		ErrorDTO errorDTO = new ErrorDTO(httpCode, message);
		return Response.status(httpCode).entity(errorDTO).header(CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
	}
	
	private String stackTraceToString(RuntimeException exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}

}
