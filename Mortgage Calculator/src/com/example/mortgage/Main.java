package com.example;

import java.util.Locale;

/**
 * Main entry point: demonstrates polymorphism, up/downcasting,
 * access modifiers, equals/hashCode, and uses abstract/report layering.
 */
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        runMortgageCalculator();
    }

    /**
     * Runs the interactive loop with type selection and reporting.
     */
    private static void runMortgageCalculator() {
        boolean keepRunning = true;

        while (keepRunning) {

            // --- Gather inputs with policy ranges ---
            int principal = (int) Console.readNumber(
                    "Principal (" + MortgagePolicy.MIN_PRINCIPAL + " - " + MortgagePolicy.MAX_PRINCIPAL + "): ",
                    MortgagePolicy.MIN_PRINCIPAL, MortgagePolicy.MAX_PRINCIPAL);

            float annualRate = (float) Console.readNumber(
                    "Annual Interest Rate (" + MortgagePolicy.MIN_RATE + " - " + MortgagePolicy.MAX_RATE + " percent): ",
                    MortgagePolicy.MIN_RATE, MortgagePolicy.MAX_RATE);

            byte years = (byte) Console.readNumber(
                    "Years (" + MortgagePolicy.MIN_YEARS + " - " + MortgagePolicy.MAX_YEARS + "): ",
                    MortgagePolicy.MIN_YEARS, MortgagePolicy.MAX_YEARS);

            System.out.println("Select Mortgage Type: 1) Fixed  2) Adjustable");
            int type = (int) Console.readNumber("Choice (1-2): ", 1, 2);

            // --- Polymorphism (upcasting) ---
            Mortgage mortgage;
            if (type == 1) {
                mortgage = new FixedRateMortgage(principal, annualRate, years); // upcast to Mortgage
            } else {
                mortgage = new AdjustableRateMortgage(principal, annualRate, years); // upcast to Mortgage
            }

            // Example: equals/hashCode semantics (same terms & same class → equal)
            Mortgage sameTerms =
                    (type == 1)
                            ? new FixedRateMortgage(principal, annualRate, years)
                            : new AdjustableRateMortgage(principal, annualRate, years);
            System.out.println("Equal to another mortgage with same terms? " + mortgage.equals(sameTerms));

            // --- Downcasting demo: only if adjustable ---
            if (mortgage instanceof AdjustableRateMortgage arm) { // pattern match downcast
                String ans = Console.readYesNo("Adjust rate before calculation? (y/n): ");
                if (ans.equals("yes")) {
                    float newRate = (float) Console.readNumber(
                            "New Annual Rate (" + MortgagePolicy.MIN_RATE + " - " + MortgagePolicy.MAX_RATE + "): ",
                            MortgagePolicy.MIN_RATE, MortgagePolicy.MAX_RATE);
                    arm.adjustAnnualRate(newRate);
                    System.out.println("Rate adjusted to: " + arm.getAnnualInterestPercent() + "%");
                }
            }

            // --- Reporting via abstract base + concrete console report ---
            MortgageReport report = new ConsoleReport(mortgage);
            report.printMortgage();
            report.printPaymentSchedule();

            String again = Console.readYesNo("\nDo you want to calculate again? (y/n): ");
            keepRunning = again.equals("yes");
        }

        System.out.println("Thanks for using the Mortgage Calculator v2.0.0!");
    }
}
