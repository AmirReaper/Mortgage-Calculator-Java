package com.example.model;

import com.example.util.MortgageMath;

/**
 * Standard fixed-rate mortgage: uses a single fixed annual rate for
 * both monthly payment and balance calculations.
 */
public class FixedRateMortgage extends Mortgage {

    public FixedRateMortgage(int principal, float annualInterestPercent, byte years) {
        super(principal, annualInterestPercent, years);
    }

    @Override
    public double calculateMonthlyPayment() {
        return MortgageMath.monthlyPayment(
                getPrincipal(), monthlyRate(), totalPayments()
        );
    }

    @Override
    public double calculateBalance(int numberOfPaymentsMade) {
        return MortgageMath.remainingBalance(
                getPrincipal(), monthlyRate(), totalPayments(), numberOfPaymentsMade
        );
    }
}
