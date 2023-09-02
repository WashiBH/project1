package com.project.credits.mapper;

import com.project.credits.entity.Credit;
import com.project.credits.model.CreditReq;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class CreditReqToEntity {
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
