package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.model.DepositRes;
import com.project.accounts.repository.AccountRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Account deposit service.
 */
@Service
public class DepositService {
  private final AccountRepository accountRepository;

  @Autowired
  public DepositService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  /**
   * Save account deposit.
   *
   * @param accountId Account id.
   * @param amount Amount to deposit.
   * @return Deposit response.
   */
  public DepositRes saveDeposit(String accountId, BigDecimal amount) {
    if (accountRepository.existsById(accountId)) {
      Account account = accountRepository.findById(accountId).orElse(null);
      account.setBalance(account.getBalance().add(amount));
      accountRepository.save(account);
      DepositRes depositRes = new DepositRes();
      depositRes.setValue("OK");
      return depositRes;
    } else {
      DepositRes depositRes = new DepositRes();
      depositRes.setValue("NO EXISTE CUENTA");
      return depositRes;
    }
  }

}
