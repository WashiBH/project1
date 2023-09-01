package com.project.transactions.mapper;

import com.project.transactions.entity.Transaction;
import com.project.transactions.model.TransactionReq;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class TransactionReqToEntity {
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
