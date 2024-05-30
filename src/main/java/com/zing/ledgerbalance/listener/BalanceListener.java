package com.zing.ledgerbalance.listener;

import com.google.gson.Gson;
import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import com.zing.ledgerbalance.service.LedgerBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class BalanceListener {
    Logger logger = LoggerFactory.getLogger(BalanceListener.class);
    private Gson gson;
    private LedgerBalanceService ledgerBalanceService;

    public BalanceListener(Gson gson, LedgerBalanceService ledgerBalanceService){
        this.gson = gson;
        this.ledgerBalanceService = ledgerBalanceService;
    }
    @KafkaListener(id = "lb-consumer", groupId = "my-group-id" ,topics = "processed-ledger-entry",containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload String payLoad) {
        try {
            ledgerBalanceService.updateLedgerEntry(gson.fromJson(payLoad, LedgerEntryEntity.class));
        }catch(Exception e){
            logger.error("Error occured while processing messages from listner:"+e.getMessage());
        }
    }
}
