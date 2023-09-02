package com.project.credits.mapper;

import com.project.credits.entity.Credit;
import com.project.credits.model.CreditRes;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreditEntityToRes {
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
