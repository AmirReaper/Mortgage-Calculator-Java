package com.example.exception;

/**
 * Custom unchecked exception when mortgage is not found.
 */
public class MortgageNotFoundException extends RuntimeException {
    public MortgageNotFoundException() {
        super("❌Mortgage not found in repository.❌");
    }

    public MortgageNotFoundException(String message) {
        super(message);
    }
}