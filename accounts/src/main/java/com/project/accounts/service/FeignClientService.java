package com.project.accounts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client service for get client type.
 */
@FeignClient(name = "clients-service", url = "http://localhost:8581")
public interface FeignClientService {
  @GetMapping("/clients/{id}/type")
  public ClientTypeRes getClientType(@PathVariable("id") String clientId);
}
