package com.cydeo.model;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Account {

    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    private Date creationDate;
    private Long userId;
    private AccountStatus accountStatus;

}
