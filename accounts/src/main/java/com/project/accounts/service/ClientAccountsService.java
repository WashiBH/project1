package com.project.accounts.service;

import com.project.accounts.entity.Account;
import com.project.accounts.mapper.AccountEntityToCheckingAccountRes;
import com.project.accounts.mapper.AccountEntityToRes;
import com.project.accounts.mapper.HolderEntityToRes;
import com.project.accounts.model.AccountClientRes;
import com.project.accounts.model.AccountReq;
import com.project.accounts.model.AccountRes;
import com.project.accounts.model.CheckingAccountRes;
import com.project.accounts.model.HolderRes;
import com.project.accounts.repository.AccountRepository;
import com.project.accounts.repository.HolderRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Accounts of the clients service.
 */
@Service
public class ClientAccountsService {

  private final AccountRepository accountRepository;
  private final HolderRepository holderRepository;

  @Autowired
  public ClientAccountsService(AccountRepository accountRepository,
                               HolderRepository holderRepository) {
    this.accountRepository = accountRepository;
    this.holderRepository = holderRepository;
  }

  /**
   * Get accounts by client.
   *
   * @param clientId Client id.
   * @return List of accounts by client.
   */
  public List<AccountClientRes> getAccountsByClient(String clientId) {
    return accountRepository.findByClientId(clientId)
      .stream()
      .map(this::mapAccountToAccountClientRes)
      .collect(Collectors.toList());
  }

  /**
   * Method to list of checking accounts of a client.
   *
   * @param clientId Client id.
   * @return List of checking accounts.
   */
  public List<CheckingAccountRes> getCheckingAccountsByClient(String clientId) {
    String accountType = AccountReq.TypeEnum.CORRIENTE.getValue();
    return accountRepository.findByClientIdAndAccountType(clientId, accountType)
      .stream()
      .map(AccountEntityToCheckingAccountRes::map)
      .collect(Collectors.toList());
  }

  /**
   * Mapper Account class to AccountClient response class.
   *
   * @param account Account object.
   * @return AccountClient response object.
   */
  private AccountClientRes mapAccountToAccountClientRes(Account account) {
    AccountRes accountRes = AccountEntityToRes.map(account);
    AccountClientRes res = new AccountClientRes();
    res.setAccountId(accountRes.getAccountId());
    res.setAccountNumber(accountRes.getAccountNumber());
    res.setType(accountRes.getType());
    res.setClient(accountRes.getClient());
    res.setBalance(accountRes.getBalance());
    res.setHolders(findHoldersByAccountId(accountRes.getAccountId()));
    return res;
  }

  /**
   * Find holders by account.
   *
   * @param accountId account id.
   * @return Lis of holders.
   */
  private List<HolderRes> findHoldersByAccountId(String accountId) {
    return holderRepository.findByAccountId(accountId)
      .stream()
      .map(HolderEntityToRes::map)
      .collect(Collectors.toList());
  }
}
