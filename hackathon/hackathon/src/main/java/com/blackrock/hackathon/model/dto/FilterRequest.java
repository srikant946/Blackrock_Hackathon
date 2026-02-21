package com.blackrock.hackathon.model.dto;

import com.blackrock.hackathon.model.entity.KPeriod;
import com.blackrock.hackathon.model.entity.PPeriod;
import com.blackrock.hackathon.model.entity.QPeriod;
import java.util.List;

// DTO For Validator
public class FilterRequest {

    private double wage;
    private List<TemporalResponse> transactions;
    private List<QPeriod> q;
    private List<PPeriod> p;
    private List<KPeriod> k;

    public double getWage() { return wage; }
    public void setWage(double wage) { this.wage = wage; }

    public List<TemporalResponse> getTransactions() { return transactions; }
    public void setTransactions(List<TemporalResponse> transactions) { this.transactions = transactions; }

    public List<QPeriod> getQ() { return q; }
    public void setQ(List<QPeriod> q) { this.q = q; }

    public List<PPeriod> getP() { return p; }
    public void setP(List<PPeriod> p) { this.p = p; }

    public List<KPeriod> getK() { return k; }
    public void setK(List<KPeriod> k) { this.k = k; }
}
