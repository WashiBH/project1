package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.AccountReq;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

/**
 * Mapper class for map AccountReq class to Account class.
 */
@Component
public class AccountReqToEntity {
  /**
   * Mapper method: AccountReq to Account.
   *
   * @param accountReq AccountReq object.
   * @param id accountId.
   * @return Account object.
   */
  public static Account map(AccountReq accountReq, String id) {
    BiFunction<AccountReq, String, Account> map = (t, u) -> {
      return new Account(
        u, "-",
        t.getType().getValue(),
        t.getClient(),
        t.getBalance()
      );
    };
    return map.apply(accountReq, id);
  }
}
