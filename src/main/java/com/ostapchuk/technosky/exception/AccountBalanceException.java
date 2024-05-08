package com.ostapchuk.technosky.exception;

public class AccountBalanceException extends RuntimeException {
    public AccountBalanceException(final String message) {
        super(message);
    }
}
