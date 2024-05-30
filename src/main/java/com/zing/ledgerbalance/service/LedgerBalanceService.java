package com.zing.ledgerbalance.service;

import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import com.zing.ledgerbalance.model.Transaction;


import java.util.List;

public interface LedgerBalanceService {
    List<Transaction> getLedgerBalance(String accountId, String dateTime);
    LedgerEntryEntity updateLedgerEntry(LedgerEntryEntity ledgerEntryEntity);
}
