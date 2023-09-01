package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.model.WithdrawalRes;
import com.project.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithdrawalService {
    private final AccountRepository accountRepository;
    @Autowired
    public WithdrawalService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public WithdrawalRes saveWithdrawal(String accountId, BigDecimal amount){
        if(accountRepository.existsById(accountId)){
            Account account = accountRepository.findById(accountId).orElse(null);
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            WithdrawalRes withdrawalRes = new WithdrawalRes();
            withdrawalRes.setValue("OK");
            return withdrawalRes;
        } else {
            WithdrawalRes withdrawalRes = new WithdrawalRes();
            withdrawalRes.setValue("NO EXISTE CUENTA");
            return withdrawalRes;
        }
    }
}
