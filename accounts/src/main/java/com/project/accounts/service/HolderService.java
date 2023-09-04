package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.entity.Holder;
import com.project.accounts.mapper.HolderEntityToRes;
import com.project.accounts.mapper.HolderReqToEntity;
import com.project.accounts.model.HolderReq;
import com.project.accounts.model.HolderRes;
import com.project.accounts.repository.AccountRepository;
import com.project.accounts.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Holder service.
 */
@Service
public class HolderService {
  private final AccountRepository accountRepository;
  private final HolderRepository holderRepository;
  private final FeignClientService clientService;

  /**
   * Constructor.
   *
   * @param holderRepository Holder repository.
   * @param accountRepository Account repository.
   * @param clientService Feign client service.
   */
  @Autowired
  public HolderService(HolderRepository holderRepository,
                       AccountRepository accountRepository,
                       FeignClientService clientService) {
    this.holderRepository = holderRepository;
    this.accountRepository = accountRepository;
    this.clientService = clientService;
  }

  /**
   * Save holder.
   *
   * @param holderReq Holder request object.
   * @return Holder response object.
   */
  public HolderRes save(HolderReq holderReq) {
    if (isValidToSaveHolder(holderReq.getAccount())) {
      Holder holder = getHolderValidatingAuthorizationSignature(holderReq, null);
      return HolderEntityToRes.map(holderRepository.save(holder));
    } else {
      return new HolderRes();
    }
  }

  /**
   * Update holder.
   *
   * @param id Holder id.
   * @param holderReq Holder request.
   * @return Holder response.
   */
  public HolderRes update(String id, HolderReq holderReq) {
    if (isValidToSaveHolder(holderReq.getAccount())) {
      Holder holder = getHolderValidatingAuthorizationSignature(holderReq, id);
      return HolderEntityToRes.map(holderRepository.save(holder));
    } else {
      return new HolderRes();
    }
  }

  /**
   * Validate if holder is valid to save.
   *
   * @param accountId Account id.
   * @return true or false.
   */
  private boolean isValidToSaveHolder(String accountId) {
    String clientId = getClientId(accountId);
    return clientId != null && isCompanyClientType(clientId);
  }

  /**
   * Get client id from account.
   *
   * @param accountId Accout id.
   * @return Client id.
   */
  private String getClientId(String accountId) {
    Account account = accountRepository.findById(accountId).orElse(null);
    return account != null ? account.getClientId() : null;
  }

  /**
   * Verify if client type is company type.
   *
   * @param clientId Client id.
   * @return true or false.
   */
  private boolean isCompanyClientType(String clientId) {
    ClientTypeRes clientType = clientService.getClientType(clientId);
    return clientType.getValue().equals("EMPRESA");
  }

  /**
   * Get validated holder if authorized or not to sign account.
   *
   * @param holderReq Holder request to validate.
   * @param holderId Holder id.
   * @return Holder object.
   */
  private Holder getHolderValidatingAuthorizationSignature(HolderReq holderReq, String holderId) {
    Holder holder = HolderReqToEntity.map(holderReq, holderId);
    if (holder.getAuthorizedSignatory().equals("SI")) {
      if (isNotValidAuthorizationSignature(holderReq.getAccount())) {
        holder.setAuthorizedSignatory("NO");
      }
    }
    return holder;
  }

  /**
   * Verify if the account already has four authorized signatories.
   *
   * @param accountId Account id.
   * @return true ro false.
   */
  private boolean isNotValidAuthorizationSignature(String accountId) {
    long numberHoldersAuthorizedSignatory = holderRepository.findByAccountId(accountId)
        .stream()
        .filter(h -> h.getAuthorizedSignatory().equals("SI"))
        .count();
    return numberHoldersAuthorizedSignatory >= 4;
  }

}
