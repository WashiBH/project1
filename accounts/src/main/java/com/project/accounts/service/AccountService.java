package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.mapper.AccountReqToEntity;
import com.project.accounts.mapper.AccountEntityToRes;
import com.project.accounts.mapper.HolderEntityToRes;
import com.project.accounts.model.AccountClientRes;
import com.project.accounts.model.AccountReq;
import com.project.accounts.model.AccountRes;
import com.project.accounts.model.HolderRes;
import com.project.accounts.repository.AccountRepository;
import com.project.accounts.repository.HolderRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final FeignClientService clientService;

    @Autowired
    public AccountService(AccountRepository accountRepository, FeignClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    public List<AccountRes> findAll(){
        return accountRepository.findAll()
                .stream()
                .map(AccountEntityToRes::map)
                .collect(Collectors.toList());
    }

    public AccountRes findAccountById(String id){
        Account account = accountRepository.findById(id).orElse(null))
        return AccountEntityToRes.map(account);
    }

    public AccountRes save(AccountReq accountReq){
        ClientTypeRes clientType = clientService.getClientType(accountReq.getClient());
        Account account;
        if (clientType.getValue().equals("PERSONA")) {
            account = savePersonAccount(accountReq);
        } else {
            account = saveCompanyAccount(accountReq);
        }
        return AccountEntityToRes.map(account);
    }

    public Account savePersonAccount(AccountReq accountReq){
        Account account = AccountReqToEntity.map(accountReq,null);
        List<Account> accounts = accountRepository.findByClientIdAndAccountType(accountReq.getClient(), accountReq.getType().getValue());
        if(accountReq.getType().equals(AccountReq.TypeEnum.AHORRO)){
            if(accounts.isEmpty()){
                account = accountRepository.save(account);
            }
        }
        if(accountReq.getType().equals(AccountReq.TypeEnum.CORRIENTE)){
            if(accounts.isEmpty()){
                account = accountRepository.save(account);
            }
        }
        if(accountReq.getType().equals(AccountReq.TypeEnum.PLAZO_FIJO)){
            if(accounts.isEmpty()){
                account = accountRepository.save(account);
            }
        }
        return account;
    }

    public Account saveCompanyAccount(AccountReq accountReq){
        Account account = AccountReqToEntity.map(accountReq, null);
        if(!accountReq.getType().equals(AccountReq.TypeEnum.AHORRO) &&
                !accountReq.getType().equals(AccountReq.TypeEnum.PLAZO_FIJO)){
            account = accountRepository.save(account);
        }
        return account;
    }

    public AccountRes update(String id, AccountReq accountReq){
        if (accountRepository.existsById(id)) {
            Account account = AccountReqToEntity.map(accountReq,id);
            account = accountRepository.save(account);
            return AccountEntityToRes.map(account);
        } else {
            return new AccountRes();
        }
    }
}
