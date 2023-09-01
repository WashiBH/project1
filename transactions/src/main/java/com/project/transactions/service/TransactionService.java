package com.project.transactions.service;

import com.project.transactions.entity.Transaction;
import com.project.transactions.mapper.TransactionDtoToEntity;
import com.project.transactions.mapper.TransactionEntityToDto;
import com.project.transactions.model.TransactionDTO;
import com.project.transactions.repository.TransactionRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDtoToEntity mapperDtoToEntity;

    @Autowired
    private TransactionEntityToDto mapperEntityToDto;

    public List<TransactionDTO> findAll(){
        return transactionRepository.findAll()
                .stream()
                .map(x -> mapperEntityToDto.map(x))
                .collect(Collectors.toList());
    }

    public TransactionDTO findTransactionById(String id){
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return mapperEntityToDto.map(transaction);
    }

    public TransactionDTO save(TransactionDTO transactionDto){
        Transaction transaction = mapperDtoToEntity.map(transactionDto);
        transaction = transactionRepository.save(transaction);
        return mapperEntityToDto.map(transaction);
    }

    public TransactionDTO update(String id, TransactionDTO transactionDto){

        if(transactionRepository.existsById(id)){
            Transaction transaction = mapperDtoToEntity.map(transactionDto);
            transaction = transactionRepository.save(transaction);
        }

        return transactionDto;
    }
}
