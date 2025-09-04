package com.example;

import java.util.Objects;

/**
 * Abstract base class representing a generic mortgage.
 * Encapsulates common fields and behavior, and defines a contract
 * for calculating monthly payment and remaining balance.
 */
public abstract class Mortgage {
    private final int principal;
    private float annualInterestPercent; // mutable for adjustable mortgages
    private final byte years;

    protected static final byte MONTHS_IN_YEAR = 12;
    protected static final byte PERCENT = 100;

    protected Mortgage(int principal, float annualInterestPercent, byte years) {
        validate(principal, annualInterestPercent, years);
        this.principal = principal;
        this.annualInterestPercent = annualInterestPercent;
        this.years = years;
    }

    protected static void validate(int principal, float rate, byte years) {
        if (principal < MortgagePolicy.MIN_PRINCIPAL || principal > MortgagePolicy.MAX_PRINCIPAL)
            throw new IllegalArgumentException("Principal out of allowed range.");
        if (rate < MortgagePolicy.MIN_RATE || rate > MortgagePolicy.MAX_RATE)
            throw new IllegalArgumentException("Rate out of allowed range.");
        if (years < MortgagePolicy.MIN_YEARS || years > MortgagePolicy.MAX_YEARS)
            throw new IllegalArgumentException("Years out of allowed range.");
    }

    /** Polymorphic monthly payment calculation. */
    public abstract double calculateMonthlyPayment();

    /** Polymorphic remaining balance after given number of months. */
    public abstract double calculateBalance(int numberOfPaymentsMade);

    /** Protected helper for subclasses. */
    protected int totalPayments() { return years * MONTHS_IN_YEAR; }

    protected float monthlyRate() {
        return (annualInterestPercent / PERCENT) / MONTHS_IN_YEAR;
    }

    // --- Accessors (encapsulation) ---
    public int getPrincipal() { return principal; }
    public float getAnnualInterestPercent() { return annualInterestPercent; }
    public byte getYears() { return years; }

    /** Used by adjustable mortgages to mutate rate safely. */
    protected void setAnnualInterestPercent(float newRate) {
        if (newRate < MortgagePolicy.MIN_RATE || newRate > MortgagePolicy.MAX_RATE)
            throw new IllegalArgumentException("Adjusted rate out of allowed range.");
        this.annualInterestPercent = newRate;
    }

    // --- Object equality: two mortgages equal if class & terms match ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mortgage)) return false;
        Mortgage that = (Mortgage) o;
        return this.getClass().equals(that.getClass())
                && principal == that.principal
                && Float.compare(annualInterestPercent, that.annualInterestPercent) == 0
                && years == that.years;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), principal, annualInterestPercent, years);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " { principal=" + principal +
                ", rate=" + annualInterestPercent +
                "%, years=" + years + " }";
    }
}
