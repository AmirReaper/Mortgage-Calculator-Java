package com.example.util;

/**
 * Final policy class holding domain validation constants.
 */
public final class MortgagePolicy {
    public static final int MIN_PRINCIPAL = 5_000;
    public static final int MAX_PRINCIPAL = 600_000;

    public static final float MIN_RATE = 3f; // percent
    public static final float MAX_RATE = 50f; // percent

    public static final byte MIN_YEARS = 1;
    public static final byte MAX_YEARS = 30;

    private MortgagePolicy() { /* no-op */ }
}
