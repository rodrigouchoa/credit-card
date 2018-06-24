package com.rodrigouchoa.creditcard.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rodrigouchoa.creditcard.domain.CreditCard;
import com.rodrigouchoa.creditcard.exception.CreditCardAlreadyExistsException;
import com.rodrigouchoa.creditcard.repository.CreditCardRepository;
import com.rodrigouchoa.creditcard.util.Utils;

@ExtendWith(SpringExtension.class)
public class InMemoryCreditCardRepositoryTest {
	
	@TestConfiguration
    static class RepositoryTestConfiguration {
  
        @Bean
        public CreditCardRepository creditCardRepository() {
            return new InMemoryCreditCardRepository();
        }
        
        @Bean
        public Utils utils() {
        	return new Utils();
        }
    }
	
	@Autowired
	private InMemoryCreditCardRepository repository;
	
	
	@BeforeEach
	public void init() {
		Set<CreditCard> db = new HashSet<>();
		
		CreditCard cc1 = new CreditCard(1L, "4645682592118366", "Ada Lovelace", new BigDecimal("999.50"));
		CreditCard cc2 = new CreditCard(2L, "4632338564755769", "Cristiano Ronaldo", new BigDecimal("1200.00"));
		CreditCard cc3 = new CreditCard(3L, "5436149371182781", "Lionel Messi", new BigDecimal("500.00"));
		
		db.addAll(Arrays.asList(cc1, cc2, cc3));
		repository.database = db;
	}
	

	@Test
	public void findById_shouldReturnCreditCard() {
		long id = 2L;
		Optional<CreditCard> op = repository.findById(2L);
		assertTrue(op.isPresent());
		assertEquals(id, op.get().getId().longValue());
	}
	
	@Test
	public void findById_shouldThrowExceptionWhenIdNull() {
		assertThrows(IllegalArgumentException.class, () -> repository.findById(null));
	}
	
	@Test
	public void findById_shouldReturnEmptyWhenIdNotFound() {
		Optional<CreditCard> op = repository.findById(99L);
		assertTrue(!op.isPresent());
	}
	
	@Test
	public void persist_shouldSaveCreditCard() {
		CreditCard expected = new CreditCard("4694198178367533", "Testing Test", new BigDecimal("100"));
		CreditCard actual = repository.persist(expected);
		assertTrue(actual.getId() != null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void persist_shouldThrowExceptionWhenCardAlreadyExists() {
		CreditCard dup = new CreditCard("4632338564755769", "Duplicate", new BigDecimal("1200.00"));
		assertThrows(CreditCardAlreadyExistsException.class, () -> repository.persist(dup));
	}
	
	@Test
	public void persist_shouldThrowExceptionWhenCardIsNull() {
		assertThrows(IllegalArgumentException.class, () -> repository.persist(null));
	}
	
	@Test
	public void findAll_shouldReturnAllCreditCards() {
		List<CreditCard> actual = repository.findAll();
		assertTrue(!actual.isEmpty());
		assertTrue(actual.size() == 3);
	}
}
