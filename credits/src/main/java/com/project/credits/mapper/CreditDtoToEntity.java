package com.project.credits.mapper;

import com.project.credits.entity.Credit;
import com.project.credits.model.CreditDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreditDtoToEntity {
    public Credit map(CreditDTO creditDTO) {
        Function<CreditDTO, Credit> map = (in) -> {
            return new Credit(
                    in.getCreditId(),
                    in.getType(),
                    in.getClient(),
                    in.getAmount()
            );
        };
        return map.apply(creditDTO);
    }
}
