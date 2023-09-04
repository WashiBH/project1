package com.project.credits.mapper;

import com.project.credits.entity.Credit;
import com.project.credits.model.CreditReq;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

/**
 * Mapper class.
 */
@Component
public class CreditReqToEntity {

  /**
   * Mapper method: Credit request class to Credit entity class.
   *
   * @param creditReq Credit request object.
   * @param id Credit id.
   * @return Credit entity object.
   */
  public static Credit map(CreditReq creditReq, String id) {
    BiFunction<CreditReq, String, Credit> map = (t, u) -> {
      return new Credit(
        u,
        t.getType().getValue(),
        t.getClient(),
        t.getAmount()
      );
    };
    return map.apply(creditReq, id);
  }
}
