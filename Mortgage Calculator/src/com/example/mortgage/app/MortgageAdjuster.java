package com.example.app;

import com.example.model.Mortgage;
import com.example.model.AdjustableRateMortgage;
import com.example.service.Console;
import com.example.util.MortgagePolicy;

/**
 * Handles adjustable rate modifications for ARM mortgages
 */
public class MortgageAdjuster {

    public static void adjustIfARM(Mortgage mortgage) {
        if (mortgage instanceof AdjustableRateMortgage arm) {
            String ans = Console.readYesNo("Adjust rate before calculation? (y/n): ");
            if (ans.equals("yes")) {
                float newRate = (float) Console.readNumber(
                        "New Annual Rate (" + MortgagePolicy.MIN_RATE + " - " + MortgagePolicy.MAX_RATE + "): ",
                        MortgagePolicy.MIN_RATE, MortgagePolicy.MAX_RATE);
                arm.adjustAnnualRate(newRate);
                System.out.println("Rate adjusted to: " + arm.getAnnualInterestPercent() + "%");
            }
        }
    }
}