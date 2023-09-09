package com.project.reports.mapper;

import com.project.reports.service.CreditRes;
import com.project.reports.service.Product;
import java.util.function.Function;
public class CreditResToProduct {

  private CreditResToProduct() {
  }

  public static Product map(CreditRes creditRes) {
    Function<CreditRes, Product> map = in -> new Product(
      in.getCreditId(),
      "CREDITOS",
      in.getType(),
      in.getAmount()
    );
    return map.apply(creditRes);
  }
}
