package com.project.credits.service;

import com.project.credits.entity.Credit;
import com.project.credits.mapper.CreditDtoToEntity;
import com.project.credits.mapper.CreditEntityToDto;
import com.project.credits.model.CreditDTO;
import com.project.credits.repository.CreditRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditDtoToEntity mapperDtoToEntity;

    @Autowired
    private CreditEntityToDto mapperEntityToDto;

    public List<CreditDTO> findAll(){
        return creditRepository.findAll()
                .stream()
                .map(x -> mapperEntityToDto.map(x))
                .collect(Collectors.toList());
    }

    public CreditDTO findCreditById(String id){
        Credit credit = creditRepository.findById(id).orElse(null);
        return mapperEntityToDto.map(credit);
    }

    public CreditDTO save(CreditDTO creditDto){
        Credit credit = mapperDtoToEntity.map(creditDto);
        credit = creditRepository.save(credit);
        return mapperEntityToDto.map(credit);
    }

    public CreditDTO update(String id, CreditDTO creditDto){

        if(creditRepository.existsById(id)){
            Credit credit = mapperDtoToEntity.map(creditDto);
            credit = creditRepository.save(credit);
        }

        return creditDto;
    }
}
