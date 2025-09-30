package com.example.app;

import com.example.model.Mortgage;
import com.example.repository.MortgageRepository;
import com.example.util.MortgagePolicy;
import java.util.Locale;

/**
 * Handles main calculator workflow - orchestrates all services
 */
public class MortgageApp {
    private static final MortgageRepository userMortgages = new MortgageRepository();

    public static void run() {
        Locale.setDefault(Locale.US);
        boolean keepRunning = true;

        while (keepRunning) {
            // Using separate services
            Mortgage mortgage = MortgageInputHandler.getMortgageFromUser();
            MortgageComparatorService.compareWithSameTerms(mortgage);
            MortgageAdjuster.adjustIfARM(mortgage);
            MortgageReportService.generateReport(mortgage);

            // Save user loan and show comparison
            userMortgages.add(mortgage);
            showUserComparison(mortgage);

            // continue the program?
            String again = com.example.service.Console.readYesNo("\nDo you want to calculate again? (y/n): ");
            keepRunning = again.equals("yes");
        }

        // Final summary
        showFinalSummary();
        System.out.println("Thanks for using the Mortgage Calculator v2.3.1!");
    }

    private static void showUserComparison(Mortgage currentMortgage) {
        MortgageComparatorService.showComparisonWithPrevious(userMortgages, currentMortgage);
    }

    private static void showFinalSummary() {
        MortgageComparatorService.showFinalSummary(userMortgages);
    }
}