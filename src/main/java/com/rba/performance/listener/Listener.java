package com.rba.performance.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rba.performance.config.JmsConfig;
import com.rba.performance.domain.model.Transaction;
import com.rba.performance.domain.service.TransactionService;

@Component
public class Listener {

	@Autowired
	private TransactionService transactionService;

	@JmsListener(destination = JmsConfig.TOPIC_ADDRESS, containerFactory = "jmsListenerContainerFactory")
	public void onReceiverTopic(String message) {
		persist(message);
	}

	@Transactional
	private void persist(String message) {
		transactionService.save(new Transaction(message));
	}

}