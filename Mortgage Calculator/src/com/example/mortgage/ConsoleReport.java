package com.example;

/**
 * Concrete report that prints to the console.
 * Also implements Exportable to simulate multiple inheritance of behavior.
 */
public class ConsoleReport extends MortgageReport implements Exportable {

    public ConsoleReport(Mortgage mortgage) {
        super(mortgage);
    }

    @Override
    public void printMortgage() {
        double mp = mortgage.calculateMonthlyPayment();
        System.out.println("\nMortgage");
        System.out.println("=======================================");
        System.out.println("Type: " + mortgage.getClass().getSimpleName());
        System.out.println("Monthly Payment: " + currency.format(mp));
    }

    @Override
    public void printPaymentSchedule() {
        System.out.println("\nPayment Schedule");
        System.out.println("=======================================");
        int total = mortgage instanceof Mortgage m ? m.totalPayments() : 0;
        for (int month = 1; month <= total; month++) {
            double balance = mortgage.calculateBalance(month);
            System.out.printf("Month %3d: %15s%n", month, currency.format(balance));
        }
    }

    @Override
    public String export() {
        StringBuilder sb = new StringBuilder();
        double mp = mortgage.calculateMonthlyPayment();
        sb.append("Type: ").append(mortgage.getClass().getSimpleName()).append('\n');
        sb.append("Monthly Payment: ").append(currency.format(mp)).append('\n');
        sb.append("Payment Schedule:\n");
        int total = mortgage instanceof Mortgage m ? m.totalPayments() : 0;
        for (int month = 1; month <= total; month++) {
            double balance = mortgage.calculateBalance(month);
            sb.append(String.format("Month %3d: %s%n", month, currency.format(balance)));
        }
        return sb.toString();
    }
}
