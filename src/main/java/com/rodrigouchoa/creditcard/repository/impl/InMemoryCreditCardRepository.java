package com.rodrigouchoa.creditcard.repository.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rodrigouchoa.creditcard.domain.CreditCard;
import com.rodrigouchoa.creditcard.exception.CreditCardAlreadyExistsException;
import com.rodrigouchoa.creditcard.repository.CreditCardRepository;
import com.rodrigouchoa.creditcard.util.Utils;

/**
 * Our make believe credit card database
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
@Repository
public class InMemoryCreditCardRepository implements CreditCardRepository {
	
	@Autowired
	private Utils utils;
	
	Set<CreditCard> database; //default visibility to facilitate tests
	AtomicLong sequence; //used to generate the surrogate key
	
	
	public InMemoryCreditCardRepository() {
		sequence = new AtomicLong(0);
		database = Collections.synchronizedSet(new HashSet<>());
	}
	

	@Override
	public Optional<CreditCard> findById(Long id) {
		utils.assertNotNull(id);
		synchronized (database) { //as we should for a synchronised collection
			Optional<CreditCard> op = database.stream().filter((obj) -> id.equals(obj.getId())).findFirst();
			return op;
		}
	}

	@Override
	public CreditCard persist(CreditCard creditCard) {
		utils.assertNotNull(creditCard);
		
		creditCard.setId(sequence.incrementAndGet());
		boolean wasAdded = database.add(creditCard);
		if (wasAdded) {
			return creditCard;
		} else {
			throw new CreditCardAlreadyExistsException("Credit card with number " + creditCard.getNumber() + " already exists.");
		}
	}

	@Override
	public List<CreditCard> findAll() {
		synchronized (database) {
			return database.stream().collect(Collectors.toList());
		}
	}
	
}
