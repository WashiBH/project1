package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.AccountReq;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class AccountReqToEntity {
    public static Account map(AccountReq accountReq, String id) {
        BiFunction<AccountReq, String, Account> map = (t, u) -> {
            return new Account(
                    u,
                    "-",
                    t.getType().getValue(),
                    t.getClient(),
                    t.getBalance()
            );
        };
        return map.apply(accountReq, id);
    }
}
