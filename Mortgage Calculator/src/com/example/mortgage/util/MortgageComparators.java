package com.example.util;

import com.example.model.Mortgage;

import java.util.Comparator;

/**
 * Factory class for mortgage comparators.
 */
public final class MortgageComparators {
    private MortgageComparators() { }

    public static Comparator<Mortgage> byPrincipal() {
        return Comparator.comparingInt(Mortgage::getPrincipal);
    }

    public static Comparator<Mortgage> byRate() {
        return Comparator.comparingDouble(Mortgage::getAnnualInterestPercent);
    }

    public static Comparator<Mortgage> byYears() {
        return Comparator.comparingInt(Mortgage::getYears);
    }
}