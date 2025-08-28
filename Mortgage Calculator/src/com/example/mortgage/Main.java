package com.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Mortgage Calculator
 * <p>
 * This program calculates monthly mortgage payments and prints the payment schedule.
 * It is designed with clean code principles and ready for future enhancements.
 * </p>
 */
public class Main {

    private static final byte MONTHS_IN_YEAR = 12;
    private static final byte PERCENT = 100;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        runMortgageCalculator();
    }

    /**
     * Runs the main loop of the mortgage calculator.
     * Handles user input, validation, and repeated calculations.
     */
    private static void runMortgageCalculator() {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            int principal = (int) Console.readNumber("Principal (5,000 - 100,000): ", 5_000, 100_000);
            float annualInterestRate = (float) Console.readNumber(
                    "Annual Interest Rate ( 1% - 30% ): ", 1, 30);
            byte years = (byte) Console.readNumber("Years ( 1 - 20 ): ", 1, 20);

            printMortgage(principal, annualInterestRate, years);
            printPaymentSchedule(principal, annualInterestRate, years);

            System.out.println("\nDo you want to calculate again? (yes/no)");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("yes")) {
                continueProgram = false;
            }
        }

        System.out.println("Thanks for using the Mortgage Calculator!");
    }

    /**
     * Prints the calculated monthly mortgage payment.
     *
     * @param principal           Loan principal amount
     * @param annualInterestRate  Annual interest rate in percentage
     * @param years               Number of years for the mortgage
     */
    private static void printMortgage(int principal, float annualInterestRate, byte years) {
        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("\nMortgage");
        System.out.println("=======================================");
        System.out.println("Monthly Payment: " + mortgageFormatted);
    }

    /**
     * Prints the full payment schedule (monthly balances).
     *
     * @param principal           Loan principal amount
     * @param annualInterestRate  Annual interest rate in percentage
     * @param years               Number of years for the mortgage
     */
    private static void printPaymentSchedule(int principal, float annualInterestRate, byte years) {
        System.out.println("\nPayment Schedule");
        System.out.println("=======================================");
        int totalPayments = years * MONTHS_IN_YEAR;

        for (int month = 1; month <= totalPayments; month++) {
            double balance = calculateBalance(principal, annualInterestRate, years, month);
            System.out.printf("Month %3d: %15s%n", month, NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    /**
     * Calculates the monthly mortgage payment.
     *
     * @param principal           Loan principal amount
     * @param annualInterestRate  Annual interest rate in percentage
     * @param years               Number of years for the mortgage
     * @return Monthly mortgage payment
     */
    public static double calculateMortgage(int principal, float annualInterestRate, byte years) {
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = years * MONTHS_IN_YEAR;

        return principal *
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }

    /**
     * Calculates the remaining balance after a number of payments.
     *
     * @param principal               Loan principal amount
     * @param annualInterestRate      Annual interest rate in percentage
     * @param years                   Number of years for the mortgage
     * @param numberOfPaymentsMade    Number of payments already made
     * @return Remaining loan balance
     */
    public static double calculateBalance(int principal, float annualInterestRate, byte years, int numberOfPaymentsMade) {
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int totalPayments = years * MONTHS_IN_YEAR;

        return principal *
                (Math.pow(1 + monthlyInterestRate, totalPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade)) /
                (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);
    }

}
