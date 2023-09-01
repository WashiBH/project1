package com.project.transactions.service;

import com.project.transactions.entity.Transaction;
import com.project.transactions.mapper.TransactionEntityToRes;
import com.project.transactions.mapper.TransactionReqToEntity;
import com.project.transactions.model.TransactionReq;
import com.project.transactions.model.TransactionRes;
import com.project.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FeignDepositService depositService;

    @Autowired
    private FeignWithdrawalService withdrawalService;

    public List<TransactionRes> findAll(){
        return transactionRepository.findAll()
                .stream()
                .map(TransactionEntityToRes::map)
                .collect(Collectors.toList());
    }

    public TransactionRes findTransactionById(String id){
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return TransactionEntityToRes.map(transaction);
    }

    public TransactionRes save(TransactionReq transactionReq){
        if(isADeposit(transactionReq) && isValidDepositInAccount(transactionReq)){
            return saveTransaction(transactionReq);
        }
        if(isAWithdrawal(transactionReq) && isValidWithdrawalInAccount(transactionReq)){
            return saveTransaction(transactionReq);
        }
        return new TransactionRes();
    }

    private boolean isADeposit(TransactionReq transactionReq){
        return transactionReq.getType().equals(TransactionReq.TypeEnum.DEPOSITO);
    }

    private boolean isValidDepositInAccount(TransactionReq transactionReq){
        DepositRes depositRes = depositService.getDepositResponse(transactionReq.getDestiny(),transactionReq.getAmount());
        return depositRes.getValue().equals("OK");
    }

    private boolean isAWithdrawal(TransactionReq transactionReq){
        return transactionReq.getType().equals(TransactionReq.TypeEnum.RETIRO);
    }

    private boolean isValidWithdrawalInAccount(TransactionReq transactionReq){
        WithdrawalRes withdrawalRes = withdrawalService.getWithdrawalResponse(transactionReq.getDestiny(),transactionReq.getAmount());
        return withdrawalRes.getValue().equals("OK");
    }

    private TransactionRes saveTransaction(TransactionReq transactionReq){
        Transaction transaction = TransactionReqToEntity.map(transactionReq,null);
        transaction = transactionRepository.save(transaction);
        return TransactionEntityToRes.map(transaction);
    }

    public TransactionRes update(String id, TransactionReq transactionReq){
        if(transactionRepository.existsById(id)){
            Transaction transaction = TransactionReqToEntity.map(transactionReq, id);
            transaction = transactionRepository.save(transaction);
            return TransactionEntityToRes.map(transaction);
        }else {
            return new TransactionRes();
        }
    }
}
