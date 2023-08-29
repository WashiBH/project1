package com.project.accounts.entity;

import com.project.accounts.model.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String accountNumber;
    private AccountDTO.TypeEnum accountType;
    private String clientDocumentNumber;
    private BigDecimal balance;
}
