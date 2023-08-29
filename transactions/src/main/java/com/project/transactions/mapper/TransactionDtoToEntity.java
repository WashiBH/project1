package com.project.transactions.mapper;

import com.project.transactions.entity.Transaction;
import com.project.transactions.model.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionDtoToEntity {
    public Transaction map(TransactionDTO transactionDTO) {
        Function<TransactionDTO, Transaction> map = (in) -> {
            return new Transaction(
                    in.getTransactionId(),
                    in.getClient(),
                    in.getType(),
                    in.getAmount(),
                    in.getOrigin(),
                    in.getDestiny()
            );
        };
        return map.apply(transactionDTO);
    }
}
