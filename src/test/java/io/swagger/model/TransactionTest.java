package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void Setup() {
        transaction = new Transaction();
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(transaction);
    }

    @Test
    public void settingTransactionAmountBelowZeroShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> transaction.setAmount((double) -1));
        assertEquals("Transaction cannot be below zero", exception.getMessage());
    }

}

