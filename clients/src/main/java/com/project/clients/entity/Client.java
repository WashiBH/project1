package com.project.clients.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
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
