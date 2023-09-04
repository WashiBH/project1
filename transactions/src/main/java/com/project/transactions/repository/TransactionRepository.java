package com.project.transactions.repository;

import com.project.transactions.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Transaction access data repository.
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
