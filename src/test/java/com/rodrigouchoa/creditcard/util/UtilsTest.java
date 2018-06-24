package com.rodrigouchoa.creditcard.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UtilsTest {
	
	@TestConfiguration
    static class UtilsTestConfiguration {
       
        @Bean
        public Utils utils() {
        	return new Utils();
        }
    }
	
	@Autowired
	private Utils utils;
	
	
	@Test
	public void testValidateCreditCardNumber() {
		assertTrue(utils.validateCreditCardNumber("4739227446322728"));
		assertTrue(utils.validateCreditCardNumber("5491886232752714"));
		assertTrue(!utils.validateCreditCardNumber("5491886232753361"));
		assertTrue(!utils.validateCreditCardNumber("3891886232752714"));
		assertTrue(!utils.validateCreditCardNumber("a89g88e2r2752743"));
	}
}
