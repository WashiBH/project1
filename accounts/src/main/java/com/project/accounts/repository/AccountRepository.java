package com.project.accounts.repository;

import com.project.accounts.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    public List<Account> findByClientIdAndAccountType(String clientId, String accountType);

    public List<Account> findByClientId(String clientId);
}
