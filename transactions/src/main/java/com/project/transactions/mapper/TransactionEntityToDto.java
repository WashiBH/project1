package com.project.transactions.mapper;

import com.project.transactions.entity.Transaction;
import com.project.transactions.model.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionEntityToDto {
    public TransactionDTO map(Transaction transaction) {
        Function<Transaction,TransactionDTO> map = (in) -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(in.getId());
            transactionDTO.setClient(in.getClientId());
            transactionDTO.setType(in.getTransactionType());
            transactionDTO.setAmount(in.getAmount());
            transactionDTO.setOrigin(in.getOriginAccount());
            transactionDTO.setDestiny(in.getDestinyAccount());
            return transactionDTO;
        };
        return map.apply(transaction);
    }
}
