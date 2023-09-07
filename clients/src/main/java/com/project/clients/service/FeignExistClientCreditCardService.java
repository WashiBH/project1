package com.project.clients.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;

@FeignClient(name = "transaction-service", url = "http://localhost:8584")
public interface FeignExistClientCreditCardService {
  @PatchMapping("/clients/{clientId}/credit-card")
  public ExistClientCreditCardRes getIfClientHasCreditCard(@PathVariable("clientId") String clientId);
}
