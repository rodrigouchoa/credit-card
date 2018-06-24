package com.rodrigouchoa.creditcard.util;

import org.springframework.stereotype.Component;

/**
 * Just an utility class
 * @author rodrigo.uchoa@gmail.com
 *
 */
@Component
public class Utils { 
	//not using static methods here to facilitate mocking/stubbing/testing if ever needed.
	
	/**
	 * Asserts an object is not null.
	 * 
	 * @param obj The object to test
	 * @throws IllegalArgumentException if the object is null
	 */
	public void assertNotNull(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Method parameter cannot be null");
		}
	}
	
	/**
	 * Asserts a String is not blank (null or empty)
	 * @param s the String to be tested
	 * @throws IllegalArgumentException if the String is null or empty
	 */
	public void assertNotNullOrEmpty(String s) {
		if (s == null || s.isEmpty()) {
			throw new IllegalArgumentException("The method parameter canot be null or empty");
		}
	}
	
	/**
	 * Luhn 10 validation
	 * 
	 * @param number the number to check
	 * @return true if valid false otherwise
	 */
	public boolean validateCreditCardNumber(String number) {
		if (number == null || number.isEmpty()) {
			return false;
		}
		
		try {
			int sum = 0;
			boolean alternate = false;
			for (int i = number.length() - 1; i >= 0; i--) {
				int n = Integer.parseInt(number.substring(i, i + 1));
				if (alternate) {
					n *= 2;
					if (n > 9) {
						n = (n % 10) + 1;
					}
				}
				sum += n;
				alternate = !alternate;
			}
			return (sum % 10 == 0);
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
