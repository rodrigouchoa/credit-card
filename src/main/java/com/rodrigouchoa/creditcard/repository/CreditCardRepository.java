package com.rodrigouchoa.creditcard.repository;

import java.util.List;
import java.util.Optional;

import com.rodrigouchoa.creditcard.domain.CreditCard;
import com.rodrigouchoa.creditcard.exception.CreditCardAlreadyExistsException;

/**
 * Interface for our repository of credit cards
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
public interface CreditCardRepository {
	
	/**
	 * Retrieves a credit card by it's surrogate id
	 * @param id the id to retrieve. Required.
	 * @return The CreditCard object wrapped by an Optional.
	 * @throws IllegalArgumentException if the id param is null
	 */
	public Optional<CreditCard> findById(Long id);
	
	/**
	 * Saves a credit card.
	 * 
	 * @param creditCard the object to save. Required.
	 * @return the newly created credit card with its id
	 * @throws CreditCardAlreadyExistsException if the credit card being saved already exists
	 * @throws IllegalArgumentException if the creditCard param is null
	 */
	public CreditCard persist(CreditCard creditCard);
	
	/**
	 * Retrieves all credit cards in no particular order.
	 * @return Collection of credit cards or empty collection. Never null.
	 */
	public List<CreditCard> findAll();
}
