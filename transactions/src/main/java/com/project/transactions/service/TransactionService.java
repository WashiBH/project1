package com.project.transactions.service;

import com.project.transactions.entity.Transaction;
import com.project.transactions.mapper.TransactionEntityToRes;
import com.project.transactions.mapper.TransactionReqToEntity;
import com.project.transactions.model.TransactionReq;
import com.project.transactions.model.TransactionRes;
import com.project.transactions.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transaction service.
 */
@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final FeignDepositService depositService;
  private final FeignWithdrawalService withdrawalService;

  /**
   * Constructor.
   *
   * @param transactionRepository Transaction repository.
   * @param depositService        Deposit service.
   * @param withdrawalService     Withdrawal service.
   */
  @Autowired
  public TransactionService(
      TransactionRepository transactionRepository,
      FeignDepositService depositService,
      FeignWithdrawalService withdrawalService
  ) {
    this.transactionRepository = transactionRepository;
    this.depositService = depositService;
    this.withdrawalService = withdrawalService;
  }

  /**
   * Find all transactions.
   *
   * @return List of transactions.
   */
  public List<TransactionRes> findAll() {
    return transactionRepository.findAll()
      .stream()
      .map(TransactionEntityToRes::map)
      .collect(Collectors.toList());
  }

  /**
   * List of client transactions at year and month.
   *
   * @param clientId clientId
   * @param year year
   * @param month month
   * @return List of client transactions.
   */
  public List<TransactionRes> getClientTransactions(String clientId, Integer year, Integer month) {
    return transactionRepository.findByClientId(clientId)
      .stream()
      .filter(transaction -> isClientTransactionAtYearAndMonth(year, month, transaction))
      .map(TransactionEntityToRes::map)
      .collect(Collectors.toList());
  }

  private boolean isClientTransactionAtYearAndMonth(
      Integer year,
      Integer month,
      Transaction transaction
  ) {
    int transactionMonth = transaction.getCreatedAt().getMonthValue();
    int transactionYear = transaction.getCreatedAt().getYear();
    return transactionYear == year && transactionMonth == month;
  }

  public TransactionRes findTransactionById(String id) {
    Transaction transaction = transactionRepository.findById(id).orElse(null);
    return TransactionEntityToRes.map(transaction);
  }

  /**
   * Save transaction.
   *
   * @param transactionReq Transaction request object.
   * @return Transaction response object.
   */
  public TransactionRes save(TransactionReq transactionReq) {
    if (isAccountDeposit(transactionReq)
        && isValidDepositInAccount(transactionReq)) {
      return saveTransaction(transactionReq);
    }
    if (isAccountWithdrawal(transactionReq)
        && isValidWithdrawalInAccount(transactionReq)) {
      return saveTransaction(transactionReq);
    }
    return new TransactionRes();
  }

  private boolean isAccountDeposit(TransactionReq transactionReq) {
    return transactionReq.getType().equals(TransactionReq.TypeEnum.DEPOSITO);
  }

  private boolean isValidDepositInAccount(TransactionReq transactionReq) {
    DepositRes depositRes = depositService.getDepositResponse(
        transactionReq.getDestiny(),
        transactionReq.getAmount()
    );
    return depositRes.getValue().equals("OK");
  }

  private boolean isAccountWithdrawal(TransactionReq transactionReq) {
    return transactionReq.getType().equals(TransactionReq.TypeEnum.RETIRO);
  }

  private boolean isValidWithdrawalInAccount(TransactionReq transactionReq) {
    WithdrawalRes withdrawalRes = withdrawalService.getWithdrawalResponse(
        transactionReq.getDestiny(),
        transactionReq.getAmount()
    );
    return withdrawalRes.getValue().equals("OK");
  }

  private TransactionRes saveTransaction(TransactionReq transactionReq) {
    Transaction transaction = TransactionReqToEntity.map(transactionReq, null);
    transaction.setCommission(calculateCommission(transactionReq));
    transaction.setCreatedAt(LocalDateTime.now());
    transaction = transactionRepository.save(transaction);
    return TransactionEntityToRes.map(transaction);
  }

  private BigDecimal calculateCommission(TransactionReq transactionReq) {
    double commission = 0;
    if (!isValidLimitTransactions(transactionReq)) {
      double amount = transactionReq.getAmount().doubleValue();
      double percentageCommission = 0.015;
      commission = amount * percentageCommission;
    }
    return BigDecimal.valueOf(commission);
  }

  private boolean isValidLimitTransactions(TransactionReq transactionReq) {
    String clientId = transactionReq.getClient();
    long limitTransactions = 20;
    List<Transaction> transactions = transactionRepository.findByClientId(clientId);
    long clientTransactionNumbers = transactions
        .stream()
        .filter(transaction ->
          isClientTransactionInThisMonth(LocalDate.now().getMonthValue(), transaction)
        )
        .count();
    return clientTransactionNumbers <= limitTransactions;
  }

  private boolean isClientTransactionInThisMonth(int month, Transaction transaction) {
    int transactionMonth = transaction.getCreatedAt().getMonthValue();
    return transactionMonth == month
      && (transaction.getTransactionType().equals(TransactionReq.TypeEnum.DEPOSITO.getValue())
      || transaction.getTransactionType().equals(TransactionReq.TypeEnum.RETIRO.getValue()));
  }

  /**
   * Update transaction.
   *
   * @param id             Transaction id.
   * @param transactionReq Transaction request object.
   * @return Transaction response object.
   */
  public TransactionRes update(String id, TransactionReq transactionReq) {
    if (transactionRepository.existsById(id)) {
      Transaction transaction = TransactionReqToEntity.map(transactionReq, id);
      transaction = transactionRepository.save(transaction);
      return TransactionEntityToRes.map(transaction);
    } else {
      return new TransactionRes();
    }
  }
}
