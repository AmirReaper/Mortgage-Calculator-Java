package com.example;

/**
 * MortgageCalculator is responsible for all mortgage-related calculations.
 * It encapsulates the loan details and provides methods to calculate
 * monthly payments and remaining balance.
 */
public class MortgageCalculator {
    private final int principal;
    private final float annualInterestRate;
    private final byte years;

    private static final byte MONTHS_IN_YEAR = 12;
    private static final byte PERCENT = 100;

    public MortgageCalculator(int principal, float annualInterestRate, byte years) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.years = years;
    }

    public double calculateMortgage() {
        float monthlyInterestRate = getMonthlyInterestRate();
        int numberOfPayments = getNumberOfPayments();

        return principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }

    public double calculateBalance(int numberOfPaymentsMade) {
        float monthlyInterestRate = getMonthlyInterestRate();
        int totalPayments = getNumberOfPayments();

        return principal
                * (Math.pow(1 + monthlyInterestRate, totalPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterestRate() {
        return annualInterestRate / PERCENT / MONTHS_IN_YEAR;
    }

    public int getPrincipal() {
        return principal;
    }

    public float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public byte getYears() {
        return years;
    }
}
