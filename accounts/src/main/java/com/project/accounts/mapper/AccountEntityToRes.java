package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.AccountRes;
import java.util.function.Function;
import org.springframework.stereotype.Component;

/**
 * Mapper class for map Account class to AccountRes class.
 */
@Component
public class AccountEntityToRes {
  private AccountEntityToRes() {
  }

  /**
   * Mapper method: Account to AccountRes.
   *
   * @param account Account object.
   * @return AccountRes object.
   */
  public static AccountRes map(Account account) {
    Function<Account, AccountRes> map = in -> {
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
