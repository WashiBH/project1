package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountDtoToEntity {
    public Account map(AccountDTO accountDTO) {
        Function<AccountDTO, Account> map = (in) -> {
            return new Account(
                    in.getAccountId(),
                    in.getAccountNumber(),
                    in.getType(),
                    in.getClient(),
                    in.getBalance()
            );
        };
        return map.apply(accountDTO);
    }
}
