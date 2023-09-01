package com.project.credits.delegate;

import com.project.credits.api.CreditsApiDelegate;
import com.project.credits.model.CreditDTO;
import com.project.credits.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditDelegate implements CreditsApiDelegate {
    private final CreditService creditService;

    @Autowired
    public CreditDelegate(CreditService creditService) {
        this.creditService = creditService;
    }

    @Override
    public ResponseEntity<CreditDTO> getCreditById(String id) {
        return ResponseEntity.ok(creditService.findCreditById(id));
    }

    @Override
    public ResponseEntity<List<CreditDTO>> getCredits() {
        return ResponseEntity.ok(creditService.findAll());
    }

    @Override
    public ResponseEntity<CreditDTO> saveCredit(CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.save(creditDTO));
    }

    @Override
    public ResponseEntity<CreditDTO> updateCredit(String id, CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.update(id, creditDTO));
    }
}
