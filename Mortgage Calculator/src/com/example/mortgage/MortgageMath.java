package com.example;

/**
 * Final math utility class for mortgage formulas.
 * Not instantiable; provides stateless, reusable helpers.
 */
public final class MortgageMath {

    private MortgageMath() { /* prevent instantiation */ }

    /**
     * Standard amortized loan monthly payment formula.
     */
    public static double monthlyPayment(int principal, float monthlyRate, int numberOfPayments) {
        if (monthlyRate == 0f) // edge case: zero interest
            return (double) principal / numberOfPayments;

        double r = monthlyRate;
        double pow = Math.pow(1 + r, numberOfPayments);
        return principal * (r * pow) / (pow - 1);
    }

    /**
     * Remaining balance after k payments for an amortized loan.
     */
    public static double remainingBalance(int principal, float monthlyRate, int totalPayments, int paid) {
        if (paid < 0 || paid > totalPayments)
            throw new IllegalArgumentException("Invalid number of payments made.");

        if (monthlyRate == 0f) // zero interest: linear paydown
            return principal * (1 - (double) paid / totalPayments);

        double r = monthlyRate;
        double powTotal = Math.pow(1 + r, totalPayments);
        double powPaid = Math.pow(1 + r, paid);
        return principal * (powTotal - powPaid) / (powTotal - 1);
    }
}
