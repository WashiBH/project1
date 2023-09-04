package com.project.transactions.mapper;

import com.project.transactions.entity.Transaction;
import com.project.transactions.model.TransactionRes;
import java.util.function.Function;
import org.springframework.stereotype.Component;

/**
 * Mapper class.
 */
@Component
public class TransactionEntityToRes {

  /**
   * Mapper method: Transaction entity class to Transaction response class.
   *
   * @param transaction Transaction entity object.
   * @return Transaction response object.
   */
  public static TransactionRes map(Transaction transaction) {
    Function<Transaction, TransactionRes> map = (in) -> {
      TransactionRes transactionRes = new TransactionRes();
      transactionRes.setTransactionId(in.getId());
      transactionRes.setClient(in.getClientId());
      transactionRes.setType(in.getTransactionType());
      transactionRes.setAmount(in.getAmount());
      transactionRes.setOrigin(in.getOriginAccount());
      transactionRes.setDestiny(in.getDestinyAccount());
      return transactionRes;
    };
    return map.apply(transaction);
  }
}
