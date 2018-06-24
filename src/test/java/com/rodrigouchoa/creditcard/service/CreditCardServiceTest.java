package com.rodrigouchoa.creditcard.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rodrigouchoa.creditcard.domain.CreditCard;
import com.rodrigouchoa.creditcard.exception.CreditCardAlreadyExistsException;
import com.rodrigouchoa.creditcard.exception.CreditCardNumberInvalidException;
import com.rodrigouchoa.creditcard.repository.CreditCardRepository;
import com.rodrigouchoa.creditcard.util.Utils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreditCardServiceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private CreditCardRepository ccRepository;
	
	@MockBean
	private Utils utils;
	
    private ParameterizedTypeReference<List<CreditCard>> listType = new ParameterizedTypeReference<List<CreditCard>>(){};
	
	@Test
	public void findAll_shouldReturnAllCreditCards() {
		CreditCard cc1 = new CreditCard(300L, "111111", "Test 1", new BigDecimal("300"));
		CreditCard cc2 = new CreditCard(400L, "222222", "Test 2", new BigDecimal("400"));
		
		List<CreditCard> expected = new ArrayList<>();
		expected.add(cc1);
		expected.add(cc2);
		
		when(ccRepository.findAll()).thenReturn(expected);
		ResponseEntity<List<CreditCard>> respEntity = restTemplate.exchange("/creditcard", HttpMethod.GET, null, listType);
		
		assertTrue(respEntity.getStatusCode().equals(HttpStatus.OK));
		assertTrue(respEntity.getBody().size() == 2);
	}
	
	@Test
	public void persist_shouldSaveCreditCard() {
		CreditCard cc = new CreditCard("4739227446322728", "Michael Jordan", new BigDecimal("100"));
		CreditCard expected = new CreditCard(99L, "4739227446322728", "Michael Jordan", new BigDecimal("100"));
		
		when(utils.validateCreditCardNumber(cc.getNumber())).thenReturn(true);
		when(ccRepository.persist(cc)).thenReturn(expected);
		ResponseEntity<CreditCard> respEntity = restTemplate.postForEntity("/creditcard", cc, CreditCard.class);
		
		assertTrue(respEntity.getStatusCode().equals(HttpStatus.CREATED));
		assertTrue(respEntity.getHeaders().getLocation() != null);
	}
	
	@Test
	public void persist_shouldThrowExceptionWhenCreditCardAlreadyExists() {
		CreditCard cc = new CreditCard("4699889516287871", "Michael Jordan", new BigDecimal("100"));
		CreditCardAlreadyExistsException ex = new CreditCardAlreadyExistsException("Already exists");
		
		when(utils.validateCreditCardNumber(cc.getNumber())).thenReturn(true);
		when(ccRepository.persist(cc)).thenThrow(ex);
		ResponseEntity<CreditCard> respEntity = restTemplate.postForEntity("/creditcard", cc, CreditCard.class);
		
		assertTrue(respEntity.getStatusCode().equals(HttpStatus.CONFLICT));
	}
	
	@Test
	public void persist_shouldThrowExceptionWhenNumberNotValid() {
		CreditCard cc = new CreditCard("4655189516287871", "Michael Jordan", new BigDecimal("100"));
		CreditCardNumberInvalidException ex = new CreditCardNumberInvalidException("Number Invalid");
		
		
		when(utils.validateCreditCardNumber(cc.getNumber())).thenThrow(ex);
		ResponseEntity<CreditCard> respEntity = restTemplate.postForEntity("/creditcard", cc, CreditCard.class);
		
		assertTrue(respEntity.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}

}
