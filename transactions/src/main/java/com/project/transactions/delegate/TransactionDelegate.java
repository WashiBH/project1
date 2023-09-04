package com.project.transactions.delegate;

import com.project.transactions.api.TransactionsApiDelegate;
import com.project.transactions.model.TransactionReq;
import com.project.transactions.model.TransactionRes;
import com.project.transactions.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Class implements TransactionsApiDelegate interface.
 */
@Service
public class TransactionDelegate implements TransactionsApiDelegate {
  private final TransactionService transactionService;

  @Autowired
  public TransactionDelegate(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public ResponseEntity<TransactionRes> getTransactionById(String id) {
    return ResponseEntity.ok(transactionService.findTransactionById(id));
  }

  @Override
  public ResponseEntity<List<TransactionRes>> getTransactions() {
    return ResponseEntity.ok(transactionService.findAll());
  }

  @Override
  public ResponseEntity<TransactionRes> saveTransaction(TransactionReq transactionReq) {
    return ResponseEntity.ok(transactionService.save(transactionReq));
  }

  @Override
  public ResponseEntity<TransactionRes> updateTransaction(
      String id,
      TransactionReq transactionReq
  ) {
    return ResponseEntity.ok(transactionService.update(id, transactionReq));
  }
}
