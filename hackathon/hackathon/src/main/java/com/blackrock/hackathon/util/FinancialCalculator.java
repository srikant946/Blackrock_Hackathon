package com.blackrock.hackathon.util;

public class FinancialCalculator {

    public static double compoundInterest(double principal, double rate, int years) {
        return principal * Math.pow(1 + rate, years);
    }

    public static double adjustForInflation(double amount, double inflationRate, int years) {
        return amount / Math.pow(1 + inflationRate, years);
    }

    public static double calculateTaxBenefit(double annualIncome, double investedAmount) {
        double eligibleDeduction = Math.min(investedAmount, Math.min(0.1 * annualIncome, 200_000));
        double originalTax = calculateTax(annualIncome);
        double reducedTax = calculateTax(annualIncome - eligibleDeduction);
        return originalTax - reducedTax;
    }

    private static double calculateTax(double income) {
        if (income <= 7_00_000) return 0;
        if (income <= 10_00_000) return 0.10 * (income - 7_00_000);
        if (income <= 12_00_000) return 0.10 * 3_00_000 + 0.15 * (income - 10_00_000);
        if (income <= 15_00_000) return 0.10 * 3_00_000 + 0.15 * 2_00_000 + 0.20 * (income - 12_00_000);
        return 0.10 * 3_00_000 + 0.15 * 2_00_000 + 0.20 * 3_00_000 + 0.30 * (income - 15_00_000);
    }
}