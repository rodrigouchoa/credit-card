package com.rodrigouchoa.creditcard.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.rodrigouchoa.creditcard.util.RuntimeExceptionMapper;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CreditCardService.class);
		register(RuntimeExceptionMapper.class);
	}
}
