package com.rodrigouchoa.creditcard.service;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.rodrigouchoa.creditcard.util.CreditCardExceptionMapper;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CreditCardService.class);
		register(CreditCardExceptionMapper.class);
	}
}
