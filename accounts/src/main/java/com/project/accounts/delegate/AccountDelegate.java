package com.project.accounts.delegate;

import com.project.accounts.api.AccountsApiDelegate;
import com.project.accounts.model.AccountDTO;
import com.project.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDelegate implements AccountsApiDelegate {
    private final AccountService accountService;

    @Autowired
    public AccountDelegate(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<AccountDTO> getAccountById(String id) {
        return ResponseEntity.ok(accountService.findAccountById(id).blockingGet());
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        return ResponseEntity.ok(accountService.findAll().blockingSingle());
    }

    @Override
    public ResponseEntity<AccountDTO> saveAccount(AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.save(accountDTO).blockingGet());
    }

    @Override
    public ResponseEntity<AccountDTO> updateAccount(String id, AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.update(id, accountDTO).blockingGet());
    }
}
