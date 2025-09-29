package com.example.app;

import com.example.model.Mortgage;
import com.example.model.FixedRateMortgage;
import com.example.model.AdjustableRateMortgage;
import com.example.service.Console;
import com.example.util.MortgagePolicy;

/**
 * Handles user input for creating mortgages
 */
public class MortgageInputHandler {

    public static Mortgage getMortgageFromUser() {
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

        return (type == 1)
                ? new FixedRateMortgage(principal, annualRate, years)
                : new AdjustableRateMortgage(principal, annualRate, years);
    }
}