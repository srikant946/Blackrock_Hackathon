package com.blackrock.hackathon.model.dto;

// DTO for Transaction
public class ExpenseRequest {

    private String date;
    private double amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
