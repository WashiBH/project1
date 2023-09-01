package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.mapper.AccountEntityToRes;
import com.project.accounts.mapper.HolderEntityToRes;
import com.project.accounts.model.AccountClientRes;
import com.project.accounts.model.AccountRes;
import com.project.accounts.model.HolderRes;
import com.project.accounts.repository.AccountRepository;
import com.project.accounts.repository.HolderRepository;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientAccountsService {

    private final AccountRepository accountRepository;
    private final HolderRepository holderRepository;

    @Autowired
    public ClientAccountsService(AccountRepository accountRepository, HolderRepository holderRepository) {
        this.accountRepository = accountRepository;
        this.holderRepository = holderRepository;
    }

    public List<AccountClientRes> getAccountsByClient(String clientId){
        return accountRepository.findByClientId(clientId)
                .stream()
                .map(this::mapAccountToAccountClientRes)
                .collect(Collectors.toList());
    }

    private AccountClientRes mapAccountToAccountClientRes(Account account){
        AccountRes accountRes = AccountEntityToRes.map(account);
        List<HolderRes> holders = holderRepository.findByAccountId(accountRes.getAccountId())
                .stream()
                .map(HolderEntityToRes::map)
                .collect(Collectors.toList());
        AccountClientRes res = new AccountClientRes();
        res.setAccountId(accountRes.getAccountId());
        res.setAccountNumber(accountRes.getAccountNumber());
        res.setType(accountRes.getType());
        res.setClient(accountRes.getClient());
        res.setBalance(accountRes.getBalance());
        res.setHolders(holders);
        return res;
    }
}
