package com.project.accounts.mapper;

import com.project.accounts.entity.Holder;
import com.project.accounts.model.HolderRes;
import java.util.function.Function;
import org.springframework.stereotype.Component;

/**
 * Mapper class for map Holder class to HolderRes class.
 */
@Component
public class HolderEntityToRes {
  /**
   * Mapper method: Holder to HolderRes.
   *
   * @param holder Holder object.
   * @return HolderRes object.
   */
  public static HolderRes map(Holder holder) {
    Function<Holder, HolderRes> map = (in) -> {
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
