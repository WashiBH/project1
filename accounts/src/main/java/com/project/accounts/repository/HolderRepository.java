package com.project.accounts.repository;

import com.project.accounts.entity.Holder;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Holder repository.
 */
@Repository
public interface HolderRepository extends MongoRepository<Holder, String> {
  public List<Holder> findByAccountId(String accountId);
}
