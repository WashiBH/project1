package com.project.accounts.mapper;

import com.project.accounts.entity.Account;
import com.project.accounts.model.CheckingAccountRes;
import java.util.function.Function;
public class AccountEntityToCheckingAccountRes {

  private AccountEntityToCheckingAccountRes() {
  }

  public static CheckingAccountRes map(Account account) {
    Function<Account, CheckingAccountRes> map = in -> {
      CheckingAccountRes accountRes = new CheckingAccountRes();
      accountRes.setAccountNumber(in.getAccountNumber());
      accountRes.setType(in.getAccountType());
      accountRes.setClient(in.getClientId());
      accountRes.setBalance(in.getBalance());
      return accountRes;
    };
    return map.apply(account);
  }
}
