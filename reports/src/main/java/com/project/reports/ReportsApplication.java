package com.project.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main application class for spring boot project.
 */
@SpringBootApplication
@EnableFeignClients
public class ReportsApplication {

  public static void main(String[] args) {

    SpringApplication.run(ReportsApplication.class, args);
  }

}
