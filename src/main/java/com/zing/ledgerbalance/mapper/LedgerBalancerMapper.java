package com.zing.ledgerbalance.mapper;

import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import com.zing.ledgerbalance.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LedgerBalancerMapper {
    public List<Transaction> mapToTransaction(List<LedgerEntryEntity> ledgerEntryEntityList){
        return ledgerEntryEntityList.stream().map(t->new Transaction(t.getAccountId(),
                t.getType(),
                t.getAmount(),
                t.getBalanceType(),
                t.getBalance(),
                t.getTimestamp()
        )).collect(Collectors.toList());
    }
}
