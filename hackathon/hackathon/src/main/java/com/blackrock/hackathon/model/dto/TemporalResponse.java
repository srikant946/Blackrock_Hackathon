package com.blackrock.hackathon.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.blackrock.hackathon.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class TemporalResponse {

    private List<TemporalResponse> valid;  // List of valid transactions
    private List<TemporalResponse> invalid; // List of invalid transactions

    @JsonView({Views.TransactionBuilderView.class, Views.TransactionValidatorView.class})
    private LocalDateTime date;

    @JsonView({Views.TransactionBuilderView.class, Views.TransactionValidatorView.class})
    private double amount;

    @JsonView({Views.TransactionBuilderView.class, Views.TransactionValidatorView.class})
    private Double ceiling;

    @JsonView({Views.TransactionBuilderView.class, Views.TransactionValidatorView.class})
    private Double remanent;

    @JsonView({Views.TransactionValidatorView.class})
    private String message;  // Only for invalid transactions

    private Boolean inkPeriod;  // Specific to valid transactions

    // Constructor for valid transactions
    public TemporalResponse(LocalDateTime date, double amount, Double ceiling, Double remanent, Boolean inkPeriod) {

        this.date = date;
        this.amount = amount;
        this.ceiling = ceiling;
        this.remanent = remanent;
        this.inkPeriod = inkPeriod;
    }

    // Constructor for invalid transactions
    public TemporalResponse(LocalDateTime date, double amount, String message) {

        this.date = date;
        this.amount = amount;
        this.message = message;
    }

    public TemporalResponse() {}

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Double getCeiling() {
        return ceiling;
    }

    public void setCeiling(Double ceiling) {
        this.ceiling = ceiling;
    }

    public Double getRemanent() {
        return remanent;
    }

    public void setRemanent(Double remanent) {
        this.remanent = remanent;
    }

    public Boolean getInkPeriod() {
        return inkPeriod;
    }

    public void setInkPeriod(Boolean inkPeriod) {
        this.inkPeriod = inkPeriod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addValidTransaction(TemporalResponse transaction) {
        if (valid == null) {
            valid = new ArrayList<>();
        }
        valid.add(transaction);
    }

    public void addInvalidTransaction(TemporalResponse transaction) {
        if (invalid == null) {
            invalid = new ArrayList<>();
        }
        invalid.add(transaction);
    }

    // Lists of valid and invalid transactions
    public List<TemporalResponse> getValid() {
        return valid;
    }

    public List<TemporalResponse> getInvalid() {
        return invalid;
    }
}
