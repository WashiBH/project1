package com.project.reports.mapper;

import com.project.reports.service.AccountRes;
import com.project.reports.service.CreditRes;
import com.project.reports.service.Product;
import java.util.function.Function;
public class AccountResToProduct {
  private AccountResToProduct() {
  }

  public static Product map(AccountRes accountRes) {
    Function<AccountRes, Product> map = in -> new Product(
      in.getAccountId(),
      "CUENTAS",
      in.getAccountType(),
      in.getBalance()
    );
    return map.apply(accountRes);
  }
}
