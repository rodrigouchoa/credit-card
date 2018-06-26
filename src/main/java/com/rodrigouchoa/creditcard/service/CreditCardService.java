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
import org.springframework.util.StringUtils;

import com.rodrigouchoa.creditcard.domain.CreditCard;
import com.rodrigouchoa.creditcard.exception.CreditCardValidationException;
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
		validate(creditCard);
		
		CreditCard persisted = repository.persist(creditCard);
		return Response.created(URI.create("/" + persisted.getId())).entity(persisted).build();
	}
	
	private void validate(CreditCard cc) {
		if (cc == null) {
			throw new CreditCardValidationException("Credit Card cannot be null.");
		}
		if (StringUtils.isEmpty(cc.getNumber())) {
			throw new CreditCardValidationException("Credit card number is required.");
		}
		if (StringUtils.isEmpty(cc.getName())) {
			throw new CreditCardValidationException("The name is required.");
		}
		if (cc.getLimit() == null) {
			throw new CreditCardValidationException("The limit is rquired");
		}
		if (!utils.validateCreditCardNumber(cc.getNumber())) {
			throw new CreditCardValidationException("The credit card number " + cc.getNumber() + " is invalid.");
		}
	}
}
