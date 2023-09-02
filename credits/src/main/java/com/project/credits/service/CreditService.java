package com.project.credits.service;

import com.project.credits.entity.Credit;
import com.project.credits.mapper.CreditEntityToRes;
import com.project.credits.mapper.CreditReqToEntity;
import com.project.credits.model.CreditReq;
import com.project.credits.model.CreditRes;
import com.project.credits.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    public List<CreditRes> findAll(){
        return creditRepository.findAll()
                .stream()
                .map(CreditEntityToRes::map)
                .collect(Collectors.toList());
    }

    public CreditRes findCreditById(String id){
        Credit credit = creditRepository.findById(id).orElse(null);
        return CreditEntityToRes.map(credit);
    }

    public CreditRes save(CreditReq creditReq){
        Credit credit = CreditReqToEntity.map(creditReq, null);
        credit = creditRepository.save(credit);
        return CreditEntityToRes.map(credit);
    }

    public CreditRes update(String id, CreditReq creditReq){

        if(creditRepository.existsById(id)){
            Credit credit = CreditReqToEntity.map(creditReq, id);
            credit = creditRepository.save(credit);
            return CreditEntityToRes.map(credit);
        } else {
            return new CreditRes();
        }
    }
}
