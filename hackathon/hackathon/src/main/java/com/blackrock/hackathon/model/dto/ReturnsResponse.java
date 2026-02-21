package com.blackrock.hackathon.model.dto;

import java.util.List;

// DTO for NPA/Index value
public class ReturnsResponse {
    private double transactionsTotalAmount;
    private double totalCeiling;
    private List<SavingsByDate> savingsByDates;

    public ReturnsResponse(double transactionsTotalAmount, double totalCeiling, List<SavingsByDate> savingsByDates) {
        this.transactionsTotalAmount = transactionsTotalAmount;
        this.totalCeiling = totalCeiling;
        this.savingsByDates = savingsByDates;
    }

    public ReturnsResponse() {

    }

    public double getTransactionsTotalAmount() {
        return transactionsTotalAmount;
    }

    public void setTransactionsTotalAmount(double transactionsTotalAmount) {
        this.transactionsTotalAmount = transactionsTotalAmount;
    }

    public double getTotalCeiling() {
        return totalCeiling;
    }

    public void setTotalCeiling(double totalCeiling) {
        this.totalCeiling = totalCeiling;
    }

    public List<SavingsByDate> getSavingsByDates() {
        return savingsByDates;
    }

    public void setSavingsByDates(List<SavingsByDate> savingsByDates) {
        this.savingsByDates = savingsByDates;
    }
}
