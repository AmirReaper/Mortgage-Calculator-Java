package com.example;

import java.util.Locale;
import java.util.Scanner;

/**
 * Main entry point of the Mortgage Calculator program.
 * It coordinates user input, calculations, and report generation.
 */
public class Main {
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
                    "Annual Interest Rate (1 - 30 Percent): ", 1, 30);
            byte years = (byte) Console.readNumber("Period (1 - 20 Years): ", 1, 20);

            MortgageCalculator calculator = new MortgageCalculator(principal, annualInterestRate, years);
            MortgageReport report = new MortgageReport(calculator);

            report.printMortgage();
            report.printPaymentSchedule();

            System.out.println("\nDo you want to calculate again? (yes/no)");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("yes")) {
                continueProgram = false;
            }
        }

        System.out.println("Thanks for using the Mortgage Calculator!");
    }
}
