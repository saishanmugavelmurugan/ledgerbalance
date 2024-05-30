package com.zing.ledgerbalance.model;

import com.zing.ledgerbalance.enums.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(

        @NotNull(message = "accountId not be null")String accountId,
        @NotNull(message = "type not be null")TransactionType type,
        @NotNull(message = "amount not be null")Double amount,
        @NotNull(message = "type not be null") TransactionType balanceType,
        @NotNull(message = "amount not be null")Double balanceAmount,
        @NotNull(message = "timestamp not be null")LocalDateTime timestamp) {
}
