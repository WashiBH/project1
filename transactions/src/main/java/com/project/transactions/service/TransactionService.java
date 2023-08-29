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

    public Observable<List<TransactionDTO>> findAll(){
        return Observable.just(transactionRepository.findAll()
                .stream()
                .map(x -> mapperEntityToDto.map(x))
                .collect(Collectors.toList())
        );
    }

    public Maybe<TransactionDTO> findTransactionById(String id){
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return Maybe.just(mapperEntityToDto.map(transaction));
    }

    public Maybe<TransactionDTO> save(TransactionDTO transactionDto){
        Transaction transaction = mapperDtoToEntity.map(transactionDto);
        transaction = transactionRepository.save(transaction);
        return Maybe.just(mapperEntityToDto.map(transaction));
    }

    public Maybe<TransactionDTO> update(String id, TransactionDTO transactionDto){

        if(transactionRepository.existsById(id)){
            Transaction transaction = mapperDtoToEntity.map(transactionDto);
            transaction = transactionRepository.save(transaction);
        }

        return Maybe.just(transactionDto);
    }
}
