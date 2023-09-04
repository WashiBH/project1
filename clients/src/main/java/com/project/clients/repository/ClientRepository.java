package com.project.clients.repository;

import com.project.clients.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Client access data repository.
 */
@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
}
