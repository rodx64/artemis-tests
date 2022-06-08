package com.rba.performance.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rba.performance.domain.model.Transaction;
import com.rba.performance.domain.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public void save(Transaction transaction) {
		transactionRepository.save(transaction);
	}
}
