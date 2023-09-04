package com.project.credits.mapper;

import com.project.credits.entity.Credit;
import com.project.credits.model.CreditRes;
import java.util.function.Function;
import org.springframework.stereotype.Component;

/**
 * Mapper class.
 */
@Component
public class CreditEntityToRes {
  /**
   * Mapper method: Credit class to Credit response class.
   *
   * @param credit Credit object.
   * @return Credit object.
   */
  public static CreditRes map(Credit credit) {
    Function<Credit, CreditRes> map = (in) -> {
      CreditRes creditRes = new CreditRes();
      creditRes.setCreditId(in.getId());
      creditRes.setType(in.getCreditType());
      creditRes.setClient(in.getClientId());
      creditRes.setAmount(in.getAmount());
      return creditRes;
    };
    return map.apply(credit);
  }
}
