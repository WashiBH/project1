package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.entity.Holder;
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
    private final ClientService clientService;

    private final HolderRepository holderRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository, ClientService clientService, HolderRepository holderRepository) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
        this.holderRepository = holderRepository;
    }

    public Observable<List<AccountRes>> findAll(){
        return Observable.just(
                accountRepository.findAll()
                        .stream()
                        .map(AccountEntityToRes::map)
                        .collect(Collectors.toList())
        );
    }

    public Maybe<AccountRes> findAccountById(String id){
        return Maybe.fromCallable(() -> accountRepository.findById(id).orElse(null))
                .map(AccountEntityToRes::map);
    }

    public Maybe<List<AccountClientRes>> getAccountsByClient(String clientId){
        return Maybe.just(
                accountRepository.findByClientId(clientId)
                        .stream()
                        .map(x -> {
                            AccountRes accountRes = AccountEntityToRes.map(x);
                            List<HolderRes> holders = holderRepository.findByAccountId(accountRes.getAccountId())
                                    .stream().map(HolderEntityToRes::map)
                                    .collect(Collectors.toList());
                            AccountClientRes res = new AccountClientRes();
                            res.setAccountId(accountRes.getAccountId());
                            res.setAccountNumber(accountRes.getAccountNumber());
                            res.setType(accountRes.getType());
                            res.setClient(accountRes.getClient());
                            res.setBalance(accountRes.getBalance());
                            res.setHolders(holders);
                            return res;
                        })
                        .collect(Collectors.toList())
        );
    }

    public Maybe<AccountRes> save(AccountReq accountReq){
        return Maybe.defer(() -> {
            ClientTypeRes clientType = clientService.getClientType(accountReq.getClient());
            Account account;

            if (clientType.getValue().equals("PERSONA")) {
                account = savePersonAccount(accountReq);
            } else {
                account = saveCompanyAccount(accountReq);
            }

            return Maybe.just(AccountEntityToRes.map(account));
        });
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

    public Maybe<AccountRes> update(String id, AccountReq accountReq){

        return Maybe.defer(() -> {
            if (accountRepository.existsById(id)) {
                Account account = AccountReqToEntity.map(accountReq,id);
                account = accountRepository.save(account);
                return Maybe.just(AccountEntityToRes.map(account));
            } else {
                return Maybe.empty();
            }
        });
    }
}
