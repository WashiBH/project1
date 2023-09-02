package com.project.credits.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class Credit {
    @Id
    private String id;
    private String creditType;
    private String clientId;
    private BigDecimal amount;
}
