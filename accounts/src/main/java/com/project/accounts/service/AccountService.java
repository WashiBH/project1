package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.mapper.AccountEntityToRes;
import com.project.accounts.mapper.AccountReqToEntity;
import com.project.accounts.model.AccountReq;
import com.project.accounts.model.AccountRes;
import com.project.accounts.repository.AccountRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Account service.
 */
@Service
public class AccountService {
  private final AccountRepository accountRepository;
  private final FeignClientService clientService;

  @Autowired
  public AccountService(AccountRepository accountRepository, FeignClientService clientService) {
    this.accountRepository = accountRepository;
    this.clientService = clientService;
  }

  /**
   * Find all account.
   *
   * @return List of account responses objects.
   */
  public List<AccountRes> findAll() {
    return accountRepository.findAll()
                            .stream()
                            .map(AccountEntityToRes::map)
                            .collect(Collectors.toList());
  }

  /**
   * Find account by accountId.
   *
   * @param id accountId.
   * @return Account response object.
   */
  public AccountRes findAccountById(String id) {
    Account account = accountRepository.findById(id).orElse(null);
    return AccountEntityToRes.map(account);
  }

  /**
   * Save account.
   *
   * @param accountReq Account request object.
   * @return Account response object.
   */
  public AccountRes save(AccountReq accountReq) {
    ClientTypeRes clientType = clientService.getClientType(accountReq.getClient());
    Account account;
    if (clientType.getValue().equals("PERSONA")) {
      account = savePersonAccount(accountReq);
    } else {
      account = saveCompanyAccount(accountReq);
    }
    return AccountEntityToRes.map(account);
  }

  /**
   * Save personal account.
   *
   * @param accountReq Account request object.
   * @return account save object.
   */
  private Account savePersonAccount(AccountReq accountReq) {
    Account account = AccountReqToEntity.map(accountReq, null);
    if (accountReq.getType().equals(AccountReq.TypeEnum.AHORRO)
        && isAccountsEmpty(accountReq.getClient(), accountReq.getType().getValue())) {
      account = accountRepository.save(account);
    }
    if (accountReq.getType().equals(AccountReq.TypeEnum.CORRIENTE)
        && isAccountsEmpty(accountReq.getClient(), accountReq.getType().getValue())) {
      account = accountRepository.save(account);
    }
    if (accountReq.getType().equals(AccountReq.TypeEnum.PLAZO_FIJO)
        && isAccountsEmpty(accountReq.getClient(), accountReq.getType().getValue())) {
      account = accountRepository.save(account);
    }
    return account;
  }

  /**
   * Validate if list accounts is empty.
   *
   * @param clientId client id for verify.
   * @param accountType account type for verify.
   * @return true or false.
   */
  private boolean isAccountsEmpty(String clientId, String accountType) {
    List<Account> accounts = accountRepository.findByClientIdAndAccountType(clientId, accountType);
    return accounts.isEmpty();
  }

  /**
   * Save company account.
   *
   * @param accountReq Account request object.
   * @return account save object.
   */
  private Account saveCompanyAccount(AccountReq accountReq) {
    Account account = AccountReqToEntity.map(accountReq, null);
    if (!accountReq.getType().equals(AccountReq.TypeEnum.AHORRO)
        && !accountReq.getType().equals(AccountReq.TypeEnum.PLAZO_FIJO)) {
      account = accountRepository.save(account);
    }
    return account;
  }

  /**
   * Update account.
   *
   * @param id Account id.
   * @param accountReq Account request.
   * @return Account response.
   */
  public AccountRes update(String id, AccountReq accountReq) {
    if (accountRepository.existsById(id)) {
      Account account = AccountReqToEntity.map(accountReq, id);
      account = accountRepository.save(account);
      return AccountEntityToRes.map(account);
    } else {
      return new AccountRes();
    }
  }
}
