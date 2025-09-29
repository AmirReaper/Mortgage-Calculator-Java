package com.example;

import java.text.NumberFormat;
import java.util.*;

/**
 * Main entry point for Mortgage Calculator v2.3.0
 * - Demonstrates OOP, Exceptions, Generics, Collections
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
        System.out.println("Thanks for using the Mortgage Calculator v2.3.0!👋✅");
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
                // Use GenericUtils.findMax(Generics + Collections)
                Mortgage mostExpensive = GenericUtils.findMax(allExceptCurrent);
                System.out.println("Your most expensive previous mortgage: " + mostExpensive);

                // Compare to current loan
                if (currentMortgage.calculateMonthlyPayment() > mostExpensive.calculateMonthlyPayment()) {
                    System.out.println("⚠️  This is your MOST expensive mortgage so far!");
                }

                // Finding the cheapest loan with Stream min
                Mortgage cheapest = allExceptCurrent.stream()
                        .min(MortgageComparators.byPrincipal())
                        .orElse(null);
                if (cheapest != null) {
                    System.out.println("Your cheapest previous mortgage: " + cheapest);
                }
            }
        }
    }

    /**
     * Final summary using Collections types
     */
    private static void showFinalSummary() {
        if (!userMortgages.getAll().isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("📊 FINAL SUMMARY (Collections Demo)");
            System.out.println("=".repeat(50));

            // 1. LIST OPERATIONS - Show all loans
            System.out.println("1. All your mortgages (List - maintains order):");
            for (Mortgage mortgage : userMortgages) { // Iterable usage
                System.out.println("   📝 " + mortgage);
            }

            // 2. SET OPERATIONS - Remove duplicates (based on equals/hashCode)
            System.out.println("\n2. Unique mortgages (Set - removes duplicates):");
            Set<Mortgage> uniqueMortgages = new HashSet<>(userMortgages.getAll());
            uniqueMortgages.forEach(m -> System.out.println("   🔄 " + m));
            System.out.println("   Original: " + userMortgages.getAll().size() +
                    ", Unique: " + uniqueMortgages.size());

            // 3. QUEUE OPERATIONS - FIFO processing
            System.out.println("\n3. Processing queue (Queue - FIFO order):");
            Queue<Mortgage> processingQueue = new LinkedList<>(userMortgages.getAll());
            int queueNumber = 1;
            while (!processingQueue.isEmpty()) {
                Mortgage nextInQueue = processingQueue.poll();
                System.out.println("   🎯 Processing #" + queueNumber++ + ": " + nextInQueue);
            }

            // 4. MAP OPERATIONS - Fast search with hashCode
            System.out.println("\n4. Quick lookup (Map - by hashCode):");
            Map<Integer, Mortgage> mortgageMap = new HashMap<>();
            for (Mortgage mortgage : userMortgages.getAll()) {
                mortgageMap.put(mortgage.hashCode(), mortgage);
                System.out.println("   🔑 Key: " + mortgage.hashCode() +
                        " → " + mortgage.getClass().getSimpleName());
            }

            // 5. STREAM OPERATIONS - Sorting and Filtering
            System.out.println("\n5. Advanced sorting (Streams + Comparators):");

            System.out.println("   Sorted by amount (low to high):");
            userMortgages.getAll().stream()
                    .sorted(MortgageComparators.byPrincipal())
                    .forEach(m -> System.out.println("      💰 " + m.getPrincipal() + " - " + m.getAnnualInterestPercent() + "%"));

            System.out.println("   Sorted by interest rate (low to high):");
            userMortgages.getAll().stream()
                    .sorted(MortgageComparators.byRate())
                    .forEach(m -> System.out.println("      📈 " + m.getAnnualInterestPercent() + "% - $" + m.getPrincipal()));

            System.out.println("   High-interest mortgages (> 4.5%):");
            userMortgages.getAll().stream()
                    .filter(m -> m.getAnnualInterestPercent() > 4.5)
                    .sorted(MortgageComparators.byRate())
                    .forEach(m -> System.out.println("      ⚠️  " + m.getAnnualInterestPercent() + "% - $" + m.getPrincipal()));

            // 6. AGGREGATE OPERATIONS - Using GenericUtils
            System.out.println("\n6. Aggregate calculations:");
            double totalMonthlyPayments = GenericUtils.totalPaymentsWildcard(userMortgages.getAll());
            System.out.println("   Total monthly payments: " +
                    NumberFormat.getCurrencyInstance().format(totalMonthlyPayments));

            Mortgage mostExpensiveOverall = GenericUtils.findMax(userMortgages.getAll());
            System.out.println("   Most expensive overall: " + mostExpensiveOverall);

            double averagePrincipal = userMortgages.getAll().stream()
                    .mapToInt(Mortgage::getPrincipal)
                    .average()
                    .orElse(0);
            System.out.println("   Average principal: " +
                    NumberFormat.getCurrencyInstance().format(averagePrincipal));
        }
    }
}
