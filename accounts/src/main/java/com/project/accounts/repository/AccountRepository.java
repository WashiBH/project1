package com.project.accounts.repository;

import com.project.accounts.entity.Account;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Account repository.
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
  public List<Account> findByClientIdAndAccountType(String clientId, String accountType);

  public List<Account> findByClientId(String clientId);
}
