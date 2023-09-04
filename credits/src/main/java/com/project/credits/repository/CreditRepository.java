package com.project.credits.repository;

import com.project.credits.entity.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Credit access data repository.
 */
@Repository
public interface CreditRepository extends MongoRepository<Credit, String> {
}
