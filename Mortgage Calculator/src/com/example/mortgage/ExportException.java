package com.example;

/**
 * Custom checked exception for report export failures.
 */
public class ExportException extends Exception {
    public ExportException(String message, Throwable cause) {
        super(message, cause);
    }
}