package com.zing.ledgerbalance.service.impl;

import com.zing.ledgerbalance.exception.LedgerBalanceNotFoundException;
import com.zing.ledgerbalance.mapper.LedgerBalancerMapper;
import com.zing.ledgerbalance.model.Transaction;
import com.zing.ledgerbalance.service.LedgerBalanceService;
import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import com.zing.ledgerbalance.db.repository.LedgerEntryRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LedgerBalanceServiceImpl implements LedgerBalanceService {
    private LedgerEntryRepository ledgerEntryRepository;
    private LedgerBalancerMapper ledgerBalancerMapper;

    public LedgerBalanceServiceImpl(LedgerEntryRepository ledgerEntryRepository,LedgerBalancerMapper ledgerBalancerMapper){
        this.ledgerEntryRepository = ledgerEntryRepository;
        this.ledgerBalancerMapper = ledgerBalancerMapper;
    }

    /**
     * Ledger balance fetched based on accountid and timestamp
     * @param accountId
     * @param dateTime
     * @return List<LedgerEntryEntity>
     */
    @Override
    public List<Transaction> getLedgerBalance(final String accountId, final String dateTime) {
        Optional<List<LedgerEntryEntity>> optionalLedgerEntryEntityList = Optional.of(ledgerEntryRepository.findByAccountIdAndTimestamp(accountId, LocalDateTime.parse(dateTime)));
        if(optionalLedgerEntryEntityList.isPresent()){
            return  ledgerBalancerMapper.mapToTransaction(optionalLedgerEntryEntityList.get());
        }else{
            throw new LedgerBalanceNotFoundException("No Record found for given data: "+ accountId+"::"+dateTime);
        }
    }

    /**
     * Save the processed ledger entry with balance.
     * @param ledgerEntryEntity
     * @return LedgerEntryEntity
     */
    @Override
    public LedgerEntryEntity updateLedgerEntry(final LedgerEntryEntity ledgerEntryEntity) {
        return ledgerEntryRepository.save(ledgerEntryEntity);
    }
}
