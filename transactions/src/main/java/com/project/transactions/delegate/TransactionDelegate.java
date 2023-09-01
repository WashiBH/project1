package com.project.transactions.delegate;

import com.project.transactions.api.TransactionsApiDelegate;
/*import com.project.transactions.model.TransactionDTO;
import com.project.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;*/
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDelegate implements TransactionsApiDelegate {
    /*private final TransactionService transactionService;

    @Autowired
    public TransactionDelegate(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<TransactionDTO> getTransactionById(String id) {
        return ResponseEntity.ok(transactionService.findTransactionById(id));
    }

    @Override
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @Override
    public ResponseEntity<TransactionDTO> saveTransaction(TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.save(transactionDTO));
    }

    @Override
    public ResponseEntity<TransactionDTO> updateTransaction(String id, TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.update(id, transactionDTO));
    }*/
}
