package com.project.transactions.repository;

import com.project.transactions.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * Transaction access data repository.
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
  public List<Transaction> findByClientId(String clientId);
}
