package com.example.app;

import com.example.model.Mortgage;
import com.example.repository.MortgageRepository;
import com.example.util.GenericUtils;
import com.example.util.MortgageComparators;
import java.text.NumberFormat;
import java.util.*;

/**
 * Handles mortgage comparisons and collections demonstrations
 */
public class MortgageComparatorService {

    public static void compareWithSameTerms(Mortgage mortgage) {
        Mortgage sameTerms = (mortgage instanceof com.example.model.FixedRateMortgage)
                ? new com.example.model.FixedRateMortgage(mortgage.getPrincipal(),
                mortgage.getAnnualInterestPercent(), mortgage.getYears())
                : new com.example.model.AdjustableRateMortgage(mortgage.getPrincipal(),
                mortgage.getAnnualInterestPercent(), mortgage.getYears());

        System.out.println("Equal to another mortgage with same terms? " + mortgage.equals(sameTerms));
    }

    public static void showComparisonWithPrevious(MortgageRepository userMortgages, Mortgage currentMortgage) {
        if (userMortgages.getAll().size() > 1) {
            System.out.println("\n--- Comparison with Your Previous Calculations ---");

            List<Mortgage> allExceptCurrent = new ArrayList<>(userMortgages.getAll());
            allExceptCurrent.remove(currentMortgage);

            if (!allExceptCurrent.isEmpty()) {
                Mortgage mostExpensive = GenericUtils.findMax(allExceptCurrent);
                System.out.println("Your most expensive previous mortgage: " + mostExpensive);

                if (currentMortgage.calculateMonthlyPayment() > mostExpensive.calculateMonthlyPayment()) {
                    System.out.println("⚠️  This is your MOST expensive mortgage so far!");
                }

                Mortgage cheapest = allExceptCurrent.stream()
                        .min(MortgageComparators.byPrincipal())
                        .orElse(null);
                if (cheapest != null) {
                    System.out.println("Your cheapest previous mortgage: " + cheapest);
                }
            }
        }
    }

    public static void showFinalSummary(MortgageRepository userMortgages) {
        if (!userMortgages.getAll().isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("📊 FINAL SUMMARY (Collections Demo)");
            System.out.println("=".repeat(50));

            // List Operations
            System.out.println("1. All your mortgages (List - maintains order):");
            for (Mortgage mortgage : userMortgages) {
                System.out.println("   📝 " + mortgage);
            }

            // Set Operations
            System.out.println("\n2. Unique mortgages (Set - removes duplicates):");
            Set<Mortgage> uniqueMortgages = new HashSet<>(userMortgages.getAll());
            uniqueMortgages.forEach(m -> System.out.println("   🔄 " + m));
            System.out.println("   Original: " + userMortgages.getAll().size() +
                    ", Unique: " + uniqueMortgages.size());

            // Queue Operations
            System.out.println("\n3. Processing queue (Queue - FIFO order):");
            Queue<Mortgage> processingQueue = new LinkedList<>(userMortgages.getAll());
            int queueNumber = 1;
            while (!processingQueue.isEmpty()) {
                Mortgage nextInQueue = processingQueue.poll();
                System.out.println("   🎯 Processing #" + queueNumber++ + ": " + nextInQueue);
            }

            // Map Operations
            System.out.println("\n4. Quick lookup (Map - by hashCode):");
            Map<Integer, Mortgage> mortgageMap = new HashMap<>();
            for (Mortgage mortgage : userMortgages.getAll()) {
                mortgageMap.put(mortgage.hashCode(), mortgage);
                System.out.println("   🔑 Key: " + mortgage.hashCode() +
                        " → " + mortgage.getClass().getSimpleName());
            }

            // Stream Operations
            System.out.println("\n5. Advanced sorting (Streams + Comparators):");

            System.out.println("   Sorted by amount (low to high):");
            userMortgages.getAll().stream()
                    .sorted(MortgageComparators.byPrincipal())
                    .forEach(m -> System.out.println("      💰 " + m.getPrincipal() + " - " + m.getAnnualInterestPercent() + "%"));

            System.out.println("   High-interest mortgages (> 4.5%):");
            userMortgages.getAll().stream()
                    .filter(m -> m.getAnnualInterestPercent() > 4.5)
                    .sorted(MortgageComparators.byRate())
                    .forEach(m -> System.out.println("      ⚠️  " + m.getAnnualInterestPercent() + "% - $" + m.getPrincipal()));

            // Aggregate Operations
            System.out.println("\n6. Aggregate calculations:");
            double totalMonthlyPayments = GenericUtils.totalPaymentsWildcard(userMortgages.getAll());
            System.out.println("   Total monthly payments: " +
                    NumberFormat.getCurrencyInstance().format(totalMonthlyPayments));

            Mortgage mostExpensiveOverall = GenericUtils.findMax(userMortgages.getAll());
            System.out.println("   Most expensive overall: " + mostExpensiveOverall);
        }
    }
}