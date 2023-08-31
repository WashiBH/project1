package com.project.accounts.mapper;

import com.project.accounts.entity.Holder;
import com.project.accounts.model.HolderRes;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class HolderEntityToRes {
    public static HolderRes map(Holder holder) {
        Function<Holder,HolderRes> map = (in) -> {
            HolderRes holderRes = new HolderRes();
            holderRes.setHolderId(in.getId());
            holderRes.setAccount(in.getAccountId());
            holderRes.setAuthorized(in.getAuthorizedSignatory());
            holderRes.setNames(in.getHolderName());
            return holderRes;
        };
        return map.apply(holder);
    }
}
