package com.zing.ledgerbalance.service;

import com.google.gson.Gson;
import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import com.zing.ledgerbalance.db.repository.LedgerEntryRepository;
import com.zing.ledgerbalance.enums.TransactionType;
import com.zing.ledgerbalance.exception.LedgerBalanceNotFoundException;
import com.zing.ledgerbalance.mapper.LedgerBalancerMapper;
import com.zing.ledgerbalance.model.Transaction;
import com.zing.ledgerbalance.service.impl.LedgerBalanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LedgerBalanceServiceImplTest {

    @InjectMocks
    private LedgerBalanceServiceImpl ledgerBalanceService;

    @Mock
    private LedgerEntryRepository ledgerEntryRepository;

    @Mock
    private Gson gson;
    @Mock
    private LedgerBalancerMapper ledgerBalancerMapper;

    @Test
    public void getLedgerBalance_whenValidInputGiven_thenReturnBalanceDetails()
            throws Exception {
        //given
        Transaction transaction = new Transaction("25235", TransactionType.DR, Double.valueOf(100), TransactionType.DR, Double.valueOf(100), LocalDateTime.parse("2024-05-28T22:22:22") );
        List<Transaction> list = Arrays.asList(transaction);

        when(ledgerBalancerMapper.mapToTransaction(any())).thenReturn(list);
        when(ledgerEntryRepository.findByAccountIdAndTimestamp(anyString(),any())).thenReturn(new ArrayList<>());

        //when
        List<Transaction> transactionList = ledgerBalanceService.getLedgerBalance("5235", "2024-05-28T22:02:55");

        assertNotNull(transactionList);

    }

    @Test
    public void getLedgerBalance_whenInputdataNotAvailableGiven_thenReturnBalanceDetails()
            throws Exception {
        //given
        when(ledgerEntryRepository.findByAccountIdAndTimestamp(anyString(),any())).thenThrow(new LedgerBalanceNotFoundException("Not found"));
        assertThrows(LedgerBalanceNotFoundException.class,()-> {
            ledgerBalanceService.getLedgerBalance("23532532","2024-05-25T11:59:59");
        });

    }

    @Test
    public void updateLedgerEntry_whenValidInputGiven_thenReturnBalanceDetails()
            throws Exception {
        //given

        when(ledgerEntryRepository.save(any())).thenReturn(new LedgerEntryEntity());

        //when
        LedgerEntryEntity ledgerEntryEntity = ledgerBalanceService.updateLedgerEntry(new LedgerEntryEntity());

        assertNotNull(ledgerEntryEntity);

    }
}
