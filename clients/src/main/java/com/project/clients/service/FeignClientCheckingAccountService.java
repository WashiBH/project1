package com.project.clients.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name = "account-service", url = "http://localhost:8582")
public interface FeignClientCheckingAccountService {
  @PatchMapping("/clients/{clientId}/checking-account")
  public List<CheckingAccountRes> getCheckingAccountsByClient(@PathVariable("clientId") String clientId);
}
