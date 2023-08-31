package com.project.accounts.mapper;

import com.project.accounts.entity.Holder;
import com.project.accounts.model.HolderReq;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class HolderReqToEntity {
    public static Holder map(HolderReq holderReq, String id) {
        BiFunction<HolderReq, String, Holder> map = (t, u) -> {
            return new Holder(
                    u,
                    t.getAccount(),
                    t.getAuthorized().getValue(),
                    t.getNames()
            );
        };
        return map.apply(holderReq, id);
    }
}
