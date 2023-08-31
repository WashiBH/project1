package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.AccountRes;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountEntityToRes {
    public static AccountRes map(Account account) {
        Function<Account,AccountRes> map = (in) -> {
            AccountRes accountRes = new AccountRes();
            accountRes.setAccountId(in.getId());
            accountRes.setAccountNumber(in.getAccountNumber());
            accountRes.setType(in.getAccountType());
            accountRes.setClient(in.getClientId());
            accountRes.setBalance(in.getBalance());
            return accountRes;
        };
        return map.apply(account);
    }
}
