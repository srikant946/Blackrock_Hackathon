package com.blackrock.hackathon.model.dto;

import java.util.List;

public class TransactionRequest {

    private double wage;
    private List<TransactionResponse> transactions;

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponse> transactions) {
        this.transactions = transactions;
    }
}
