package com.project.accounts.repository;

import com.project.accounts.entity.Holder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolderRepository extends MongoRepository<Holder, String> {
    public List<Holder> findByAccountId(String accountId);

}
