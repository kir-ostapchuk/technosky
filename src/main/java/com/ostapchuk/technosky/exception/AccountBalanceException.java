package com.ostapchuk.technosky.exception;

public class AccountBalanceException extends RuntimeException {
    public AccountBalanceException(final String message) {
        super(message);
    }

    public AccountBalanceException(final Long userId) {
        super("Account of " + userId + " does not have enough balance to transfer");
    }
}
