package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountEntityToDto {
    public AccountDTO map(Account account) {
        Function<Account,AccountDTO> map = (in) -> {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountId(in.getId());
            accountDTO.setAccountNumber(in.getAccountNumber());
            accountDTO.setType(in.getAccountType());
            accountDTO.setClient(in.getClientDocumentNumber());
            accountDTO.setBalance(in.getBalance());
            return accountDTO;
        };
        return map.apply(account);
    }
}
