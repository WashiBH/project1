package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.mapper.AccountDtoToEntity;
import com.project.accounts.mapper.AccountEntityToDto;
import com.project.accounts.model.AccountDTO;
import com.project.accounts.repository.AccountRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountDtoToEntity mapperDtoToEntity;

    @Autowired
    private AccountEntityToDto mapperEntityToDto;

    public Observable<List<AccountDTO>> findAll(){
        return Observable.just(accountRepository.findAll()
                .stream()
                .map(x -> mapperEntityToDto.map(x))
                .collect(Collectors.toList())
        );
    }

    public Maybe<AccountDTO> findAccountById(String id){
        Account account = accountRepository.findById(id).orElse(null);
        return Maybe.just(mapperEntityToDto.map(account));
    }

    public Maybe<AccountDTO> save(AccountDTO accountDto){
        Account account = mapperDtoToEntity.map(accountDto);
        account = accountRepository.save(account);
        return Maybe.just(mapperEntityToDto.map(account));
    }

    public Maybe<AccountDTO> update(String id, AccountDTO accountDto){

        if(accountRepository.existsById(id)){
            Account account = mapperDtoToEntity.map(accountDto);
            account = accountRepository.save(account);
        }

        return Maybe.just(accountDto);
    }
}
