package com.project.accounts.mapper;

import com.project.accounts.entity.Holder;
import com.project.accounts.model.HolderReq;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

/**
 * Mapper class for map HolderReq class to Holder class.
 */
@Component
public class HolderReqToEntity {
  /**
   * Method mapper: HolderReq to Holder.
   *
   * @param holderReq HolderReq object.
   * @param id holderId.
   * @return Holder object.
   */
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
