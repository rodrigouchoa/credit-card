package com.rodrigouchoa.creditcard.service;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigouchoa.creditcard.domain.CreditCard;
import com.rodrigouchoa.creditcard.exception.CreditCardNumberInvalidException;
import com.rodrigouchoa.creditcard.repository.CreditCardRepository;
import com.rodrigouchoa.creditcard.util.Utils;

/**
 * The service/endpoint.
 * 
 * In some cases, might be ideal to separate the actual endpoint (aka controller for those who like Spring MVC)
 * from the business logic (service). For this example, I took the liberty of having just a single layer for both
 * for the sake of simplicity.
 * 
 * @author rodrigo.uchoa@gmail.com
 *
 */
@Service
@Path("/creditcard")
public class CreditCardService {
	
	@Autowired
	private CreditCardRepository repository;
	
	@Autowired
	private Utils utils;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CreditCard> findAll() {
		return repository.findAll();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response persist(CreditCard creditCard) {
		boolean isNumberValid = utils.validateCreditCardNumber(creditCard.getNumber());
		if (!isNumberValid) {
			throw new CreditCardNumberInvalidException("The credit card number " + creditCard.getNumber() + " is invalid.");
		}
		CreditCard persisted = repository.persist(creditCard);
		return Response.created(URI.create("/" + persisted.getId())).entity(persisted).build();
	}
}
