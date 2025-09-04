package com.example;

import java.text.NumberFormat;

/**
 * Abstract report that knows *what* to show but not *how* to output.
 * Subclasses decide the output channel (console, file, etc.).
 */
public abstract class MortgageReport {
    protected final Mortgage mortgage;
    protected final NumberFormat currency = NumberFormat.getCurrencyInstance();

    protected MortgageReport(Mortgage mortgage) {
        this.mortgage = mortgage;
    }

    public abstract void printMortgage();
    public abstract void printPaymentSchedule();
}
