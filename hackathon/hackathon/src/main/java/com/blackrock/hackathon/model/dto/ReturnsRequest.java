package com.blackrock.hackathon.model.dto;

import com.blackrock.hackathon.model.entity.KPeriod;

import java.util.List;

// DTO for NPA/Index Returns
public class ReturnsRequest {
    private int age;
    private double wage;
    private double inflation;
    private List<KPeriod> k;
    private List<TemporalResponse> transactions;

    public ReturnsRequest(int age, double wage, double inflation, List<KPeriod> k, List<TemporalResponse> transactions) {
        this.age = age;
        this.wage = wage;
        this.inflation = inflation;
        this.k = k;
        this.transactions = transactions;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public List<KPeriod> getK() {
        return k;
    }

    public void setK(List<KPeriod> k) {
        this.k = k;
    }

    public List<TemporalResponse> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TemporalResponse> transactions) {
        this.transactions = transactions;
    }
}
