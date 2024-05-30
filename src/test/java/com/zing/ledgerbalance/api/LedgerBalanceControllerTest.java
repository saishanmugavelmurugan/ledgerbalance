package com.zing.ledgerbalance.api;

import com.zing.ledgerbalance.enums.TransactionType;
import com.zing.ledgerbalance.exception.LedgerBalanceExceptionHandler;
import com.zing.ledgerbalance.exception.LedgerBalanceNotFoundException;
import com.zing.ledgerbalance.model.Transaction;
import com.zing.ledgerbalance.service.LedgerBalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
public class LedgerBalanceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private LedgerBalanceController ledgerBalanceController;
    @Mock
    private LedgerBalanceService ledgerBalanceService;


    @BeforeEach
    public void setUp() {
        this.mockMvc = standaloneSetup(ledgerBalanceController)
                .setControllerAdvice(new LedgerBalanceExceptionHandler()).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLedgerBalance_whenValidDateGiven_thenReturnLedgerBalanceWithStatus200()
            throws Exception {


        Transaction transaction= new Transaction("141424", TransactionType.DR,
                Double.valueOf(1000),TransactionType.DR,Double.valueOf(1000), LocalDateTime.MAX

        );
        List<Transaction> ledgerBalanceList = new ArrayList<>();
        ledgerBalanceList.add(transaction);
        when(ledgerBalanceService.getLedgerBalance(anyString(), anyString()))
                .thenReturn(ledgerBalanceList);
        //when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/lb/3532532/2024-05-17T22:33:44")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());

        //then
        verify(ledgerBalanceService, times(1)).getLedgerBalance(any(), any());
    }
    @Test
    public void getLedgerBalance_whenValidDateNORecordFound__thenThrow404notFoundException()
            throws Exception {

        //given
        when(ledgerBalanceService.getLedgerBalance(anyString(), anyString()))
                .thenThrow(new LedgerBalanceNotFoundException("NO Data found for Give inputs."));

        //when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/lb/3532532/2024-05-17T22:33:44")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound());
    }

}
