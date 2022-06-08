package com.rba.performance.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rba.performance.domain.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
