package com.blackrock.hackathon.model.dto;

import java.time.LocalDateTime;

// Getting savings by date
public class SavingsByDate {
    private LocalDateTime start;
    private LocalDateTime end;
    private double amount;
    private double profits;
    private double taxBenefit;

    public SavingsByDate(LocalDateTime start, LocalDateTime end, double amount, double profits, double taxBenefit) {
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.profits = profits;
        this.taxBenefit = taxBenefit;
    }

    public SavingsByDate() {

    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getProfits() {
        return profits;
    }

    public void setProfits(double profits) {
        this.profits = profits;
    }

    public double getTaxBenefit() {
        return taxBenefit;
    }

    public void setTaxBenefit(double taxBenefit) {
        this.taxBenefit = taxBenefit;
    }
}
