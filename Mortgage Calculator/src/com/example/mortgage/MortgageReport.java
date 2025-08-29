package com.example;

import java.text.NumberFormat;

/**
 * MortgageReport is responsible for displaying the results of calculations
 * to the user. It separates business logic (calculations) from presentation.
 */
public class MortgageReport {
    private final MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
    }

    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("\nMortgage");
        System.out.println("=======================================");
        System.out.println("Monthly Payment: " + mortgageFormatted);
    }

    public void printPaymentSchedule() {
        System.out.println("\nPayment Schedule");
        System.out.println("=======================================");

        for (int month = 1; month <= calculator.getYears() * 12; month++) {
            double balance = calculator.calculateBalance(month);
            System.out.printf("Month %3d: %15s%n", month, NumberFormat.getCurrencyInstance().format(balance));
        }
    }
}
