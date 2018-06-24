package com.rodrigouchoa.creditcard.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.rodrigouchoa.creditcard.util.Utils;

/**
 * The CreditCard domain entity.
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
@JsonIgnoreProperties(value = { "utils" })
public class CreditCard implements Serializable {
	/* surrogate key, even though the number should be unique (as far as my understading of credit cards go).
	 * I like to have one even though some people don't.*/
	private Long id;
	
	private String number; //String since numbers can start with 0
	private String name;
	private BigDecimal limit;
	private BigDecimal balance;
	
	
	public CreditCard() { //jackson needs this one
		balance = new BigDecimal("0.00");
	} 
	
	public CreditCard(Long id, String number, String name, BigDecimal limit) {
		this(number, name, limit);
		
		Utils utils = new Utils();
		utils.assertNotNull(id);
		this.id = id;
	}

	public CreditCard(String number, String name, BigDecimal limit) {
		Utils utils = new Utils();
		utils.assertNotNullOrEmpty(number);
		utils.assertNotNullOrEmpty(name);
		utils.assertNotNull(limit);
		
		this.number = number;
		this.name = name;
		this.limit = limit;
		this.balance = new BigDecimal("0.00");
	}
	
	
	/* returns the JSON representation of the object */
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	@Override
	public int hashCode() {
		return getNumber().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CreditCard)) {
			return false;
		}
		CreditCard other = (CreditCard) obj;
		return this.getNumber().equals(other.getNumber());
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	/**
	 * Will return the limit of this credit cards, rounded to 2 decimal places.
	 * @return the limit
	 */
	public BigDecimal getLimit() {
		return limit.setScale(2, RoundingMode.HALF_EVEN);
	}

	/**
	 * Will return the balance rounded to 2 decimal digits
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance.setScale(2, RoundingMode.HALF_EVEN);
	}
	
}
