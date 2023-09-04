package com.project.accounts.delegate;

import com.project.accounts.api.AccountsApiDelegate;
import com.project.accounts.model.AccountReq;
import com.project.accounts.model.AccountRes;
import com.project.accounts.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Class implements AccountsApiDelegate interface generate by OpenAPI generator.
 */
@Service
public class AccountDelegate implements AccountsApiDelegate {
  private final AccountService accountService;

  @Autowired
  public AccountDelegate(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public ResponseEntity<AccountRes> getAccountById(String id) {
    return ResponseEntity.ok(accountService.findAccountById(id));
  }

  @Override
  public ResponseEntity<List<AccountRes>> getAccounts() {
    return ResponseEntity.ok(accountService.findAll());
  }

  @Override
  public ResponseEntity<AccountRes> saveAccount(AccountReq accountReq) {
    return ResponseEntity.ok(accountService.save(accountReq));
  }

  @Override
  public ResponseEntity<AccountRes> updateAccount(String id, AccountReq accountReq) {
    return ResponseEntity.ok(accountService.update(id, accountReq));
  }
}
