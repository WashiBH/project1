package com.project.transactions.mapper;

import com.project.transactions.entity.Transaction;
import com.project.transactions.model.TransactionReq;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

/**
 * Mapper class.
 */
@Component
public class TransactionReqToEntity {

  /**
   * Mapper method: Transaction request class to Transaction entity class.
   *
   * @param transactionReq Transaction request object.
   * @param id Transaction id.
   * @return Transaction response object.
   */
  public static Transaction map(TransactionReq transactionReq, String id) {
    BiFunction<TransactionReq, String, Transaction> map = (t, u) -> {
      return new Transaction(
        u,
        t.getClient(),
        t.getType().getValue(),
        t.getAmount(),
        t.getOrigin(),
        t.getDestiny()
      );
    };
    return map.apply(transactionReq, id);
  }
}
