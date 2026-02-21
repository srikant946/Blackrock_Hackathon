package com.blackrock.hackathon.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.blackrock.hackathon.views.Views;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {

    private List<TransactionResponse> valid;  // List of valid transactions
    private List<TransactionResponse> invalid; // List of invalid transactions

    @JsonView({Views.TransactionBuilderView.class, Views.TransactionValidatorView.class})
    private String date;

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
    public TransactionResponse(LocalDateTime date, double amount, Double ceiling, Double remanent, Boolean inkPeriod) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.date = date != null ? date.format(formatter) : null;

        this.amount = amount;
        this.ceiling = ceiling;
        this.remanent = remanent;
        this.inkPeriod = inkPeriod;
    }

    // Constructor for invalid transactions
    public TransactionResponse(LocalDateTime date, double amount, String message) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.date = date != null ? date.format(formatter) : null;
        this.amount = amount;
        this.message = message;
    }

    public TransactionResponse() {}

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public void addValidTransaction(TransactionResponse transaction) {
        if (valid == null) {
            valid = new ArrayList<>();
        }
        valid.add(transaction);
    }

    public void addInvalidTransaction(TransactionResponse transaction) {
        if (invalid == null) {
            invalid = new ArrayList<>();
        }
        invalid.add(transaction);
    }

    // Lists of valid and invalid transactions
    public List<TransactionResponse> getValid() {
        return valid;
    }

    public List<TransactionResponse> getInvalid() {
        return invalid;
    }
}
