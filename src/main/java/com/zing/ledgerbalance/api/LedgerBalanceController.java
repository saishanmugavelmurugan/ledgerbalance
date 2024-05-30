package com.zing.ledgerbalance.api;

import com.zing.ledgerbalance.db.entity.LedgerEntryEntity;
import com.zing.ledgerbalance.model.Transaction;
import com.zing.ledgerbalance.service.LedgerBalanceService;
import com.zing.ledgerbalance.service.LedgerBalanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lb")
public class LedgerBalanceController {

    private LedgerBalanceService ledgerBalanceService;
    public LedgerBalanceController(LedgerBalanceService ledgerBalanceService) {
        this.ledgerBalanceService = ledgerBalanceService;

    }

    /**
     * This api helps for ledger posting
     * @return String - posting message
     */
    @GetMapping("/{accountId}/{dateTime}")
    public List<Transaction> getLedgerBalance(@Valid @PathVariable String accountId, @Valid @PathVariable String dateTime) {
        return ledgerBalanceService.getLedgerBalance(accountId,dateTime);
    }
}
