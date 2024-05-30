package com.zing.ledgerbalance.exception;

public class LedgerBalanceNotFoundException extends RuntimeException{
    public LedgerBalanceNotFoundException(final String s) {
        super(s);
    }

    LedgerBalanceNotFoundException(final Throwable cause) {
        super(cause);
    }

}
