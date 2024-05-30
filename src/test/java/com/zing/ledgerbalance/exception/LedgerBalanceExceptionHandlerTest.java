package com.zing.ledgerbalance.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
public class LedgerBalanceExceptionHandlerTest {
    @InjectMocks
    private LedgerBalanceExceptionHandler ledgerBalanceExceptionHandler;


    @Test
    public void whenLedgerBalanceNotFoundExceptionIsThrown_thenShouldReturnResponseStatus_NotFoundError() {
        //Given
        LedgerBalanceNotFoundException ledgerNotFoundException = new LedgerBalanceNotFoundException("not found");

        //When
        ResponseEntity<Object> responseError = ledgerBalanceExceptionHandler.handleNotFound(ledgerNotFoundException);

        //Then
        assertThat(responseError.getStatusCode(), is(equalTo(NOT_FOUND)));

    }

    @Test
    public void whenAnyExceptionIsThrown_thenShouldReturnResponseStatus_500() {
        //Given
        Exception exception = new Exception("invalid data");

        //When
        ResponseEntity<Object> responseError = ledgerBalanceExceptionHandler.handleExceptionRequest(exception);

        //Then
        assertThat(responseError.getStatusCode(), is(equalTo(INTERNAL_SERVER_ERROR)));

    }

}
