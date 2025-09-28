package com.example;

import java.text.NumberFormat;
import java.util.*;

/**
 * Main entry point for Mortgage Calculator v2.2.0
 * - Demonstrates OOP, Exceptions, Generics
 */
public class Main {
    private static final MortgageRepository userMortgages = new MortgageRepository();

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

            // Save user loan in Repository
            userMortgages.add(mortgage);

            // Example: equals/hashCode semantics (same terms & same class → equal)
            Mortgage sameTerms =
                    (type == 1)
                            ? new FixedRateMortgage(principal, annualRate, years)
                            : new AdjustableRateMortgage(principal, annualRate, years);
            System.out.println("Equal to another mortgage with same terms? " + mortgage.equals(sameTerms));

            // --- Down-casting demo: only if adjustable ---
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

            // --- Show comparison with the user's previous loans
            showComparisonWithPrevious(mortgage);

            // export file
            try {
                FileReportExporter exporter = new FileReportExporter("mortgage_report.txt");
                exporter.exportToFile((Exportable) report);
                System.out.println("Report exported to mortgage_report.txt");
            } catch (ExportException e) {
                System.out.println("❌Export failed: " + e.getMessage());
            }

            String again = Console.readYesNo("\nDo you want to calculate again? (y/n): ");
            keepRunning = again.equals("yes");
        }

        showFinalSummary();
        System.out.println("Thanks for using the Mortgage Calculator v2.1.0!👋✅");
    }

    /**
     * Compare current loan with user's previous loans
     */
    private static void showComparisonWithPrevious(Mortgage currentMortgage) {
        if (userMortgages.getAll().size() > 1) {
            System.out.println("\n--- Comparison with Your Previous Calculations ---");

            // Find the user's most expensive loan
            List<Mortgage> allExceptCurrent = new ArrayList<>(userMortgages.getAll());
            allExceptCurrent.remove(currentMortgage);


            if (!allExceptCurrent.isEmpty()) {
                Mortgage mostExpensive = GenericUtils.findMax(allExceptCurrent);
                System.out.println("Your most expensive previous mortgage: " + mostExpensive);

                // Compare to current loan
                if (currentMortgage.calculateMonthlyPayment() > mostExpensive.calculateMonthlyPayment()) {
                    System.out.println("⚠️  This is your MOST expensive mortgage so far!");
                }
            }
        }
    }

    /**
     * Final summary when user exits
     */
    private static void showFinalSummary() {
        if (!userMortgages.getAll().isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("📊 FINAL SUMMARY");
            System.out.println("=".repeat(50));

            System.out.println("Total mortgages calculated: " + userMortgages.getAll().size());

            // Use GenericUtils to calculate the sum
            double totalMonthlyPayments = GenericUtils.totalPaymentsWildcard(userMortgages.getAll());
            System.out.println("Total monthly payments across all mortgages: " +
                    NumberFormat.getCurrencyInstance().format(totalMonthlyPayments));

            // Sorting with Comparators
            if (userMortgages.getAll().size() > 1) {
                System.out.println("\nMortgages sorted by amount (low to high):");
                userMortgages.getAll().stream()
                        .sorted(MortgageComparators.byPrincipal())
                        .forEach(m -> System.out.println("  • " + m));

                System.out.println("\nMortgages sorted by interest rate (low to high):");
                userMortgages.getAll().stream()
                        .sorted(MortgageComparators.byRate())
                        .forEach(m -> System.out.println("  • " + m.getAnnualInterestPercent() + "% - $" + m.getPrincipal()));
            }
        }
    }
}
