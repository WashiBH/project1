package com.project.clients.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Client entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {
  @Id
  private String id;
  private String clientType;
  private String name;
  private String fatherLastName;
  private String motherLastName;
  private String businessName;
  private String documentType;
  private String documentNumber;
  private String birthdate;
  private String address;
  private String phoneNumber;
  private String email;

}
