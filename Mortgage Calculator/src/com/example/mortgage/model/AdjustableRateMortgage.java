package com.example.model;

import com.example.util.MortgageMath;

/**
 * Adjustable-Rate Mortgage (ARM) example.
 * For simplicity, monthly payment is computed using the *current* annual rate.
 * You can call adjustAnnualRate(...) and then re-run calculations.
 */
public class AdjustableRateMortgage extends Mortgage implements Adjustable {

    public AdjustableRateMortgage(int principal, float annualInterestPercent, byte years) {
        super(principal, annualInterestPercent, years);
    }

    @Override
    public void adjustAnnualRate(float newAnnualPercent) {
        // Uses protected setter from base class (access via 'super' API)
        setAnnualInterestPercent(newAnnualPercent);
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
