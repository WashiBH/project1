package com.project.credits.mapper;

import com.project.credits.entity.Credit;
import com.project.credits.model.CreditDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreditEntityToDto {
    public CreditDTO map(Credit credit) {
        Function<Credit,CreditDTO> map = (in) -> {
            CreditDTO creditDTO = new CreditDTO();
            creditDTO.setCreditId(in.getId());
            creditDTO.setType(in.getCreditType());
            creditDTO.setClient(in.getClientId());
            creditDTO.setAmount(in.getAmount());
            return creditDTO;
        };
        return map.apply(credit);
    }
}
