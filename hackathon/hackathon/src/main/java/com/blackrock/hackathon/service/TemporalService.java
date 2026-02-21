package com.blackrock.hackathon.service;

import com.blackrock.hackathon.model.dto.TemporalResponse;
import com.blackrock.hackathon.model.entity.KPeriod;
import com.blackrock.hackathon.model.entity.PPeriod;
import com.blackrock.hackathon.model.entity.QPeriod;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TemporalService {

    private List<KPeriod> kPeriodList;

    // Method to check if a transaction falls within any k period
    private boolean isInKPeriod(LocalDateTime transactionDate) {
        for (KPeriod kPeriod : kPeriodList) {
            if (!transactionDate.isBefore(kPeriod.getStart()) && !transactionDate.isAfter(kPeriod.getEnd())) {
                return true; // Transaction date is within the current k period
            }
        }
        return false; // Transaction date is not within any k period
    }

    // Apply q and p periods on the transactions
    public List<TemporalResponse> applyQandPPeriods(
            List<TemporalResponse> transactions,
            List<QPeriod> qPeriods,
            List<PPeriod> pPeriods,
            List<KPeriod> kPeriods
    ) {
        kPeriodList = kPeriods;
        for (TemporalResponse t : transactions) {
            // Apply q-period fixed remanent
            double applicableFixed = -1;
            for (QPeriod q : qPeriods) {
                if (!t.getDate().isBefore(q.getStart()) && !t.getDate().isAfter(q.getEnd())) {
                    double finalApplicableFixed = applicableFixed;
                    if (applicableFixed == -1 || q.getStart().isAfter(qPeriods.stream().filter(x -> x.getFixed() == finalApplicableFixed).findFirst().get().getStart())) {
                        applicableFixed = q.getFixed();
                    }
                }
            }
            if (applicableFixed >= 0) {
                t.setRemanent(applicableFixed); // Set the fixed remanent for the transaction
            }

            // Apply p-period extra remanent
            double extra = 0;
            for (PPeriod p : pPeriods) {

                if (!t.getDate().isBefore(p.getStart()) && !t.getDate().isAfter(p.getEnd())) {
                    extra += p.getExtra();
                }
            }
            t.setRemanent(t.getRemanent() + extra);
        }
        return transactions;
    }

    public Map<String, List<TemporalResponse>> validateTransactions(List<TemporalResponse> transactions, double wage) {
        // Lists to store valid and invalid transactions
        List<TemporalResponse> validTransactions = new ArrayList<>();
        List<TemporalResponse> invalidTransactions = new ArrayList<>();

        // Set to track unique date and amount pairs to detect duplicates
        Set<String> seenTransactions = new HashSet<>();

        for (TemporalResponse transaction : transactions) {

            // Create a unique identifier based on the date and amount
            String transactionKey = transaction.getDate() + ":" + transaction.getAmount();

            // Check for duplicate transactions
            if (seenTransactions.contains(transactionKey)) {


                // It's a duplicate, so add it to invalid transactions
                invalidTransactions.add(new TemporalResponse(
                        transaction.getDate(),
                        transaction.getAmount(),
                        "Duplicate transaction"
                ));
            } else if (transaction.getAmount() < 0) {
                // It's an invalid transaction due to negative amount
                invalidTransactions.add(new TemporalResponse(
                        transaction.getDate(),
                        transaction.getAmount(),
                        "Negative Amounts are not allowed"
                ));
            } else {
                // It's a valid transaction
                // Set inkPeriod only for valid transactions (example check, can be replaced with your actual logic)
                if (isInKPeriod(transaction.getDate())) {
                    transaction.setInkPeriod(true);  // Set inkPeriod to true for valid transactions
                }

                // Add valid transaction to the valid list
                validTransactions.add(transaction);
                seenTransactions.add(transactionKey); // Add to the seen set to track duplicates
            }
        }

        // Returning valid and invalid transactions in a map
        Map<String, List<TemporalResponse>> result = new HashMap<>();
        result.put("valid", validTransactions);
        result.put("invalid", invalidTransactions);

        return result;
    }

    // Calculate the total remanent for each k period
    public Map<KPeriod, Double> calculateKPeriodTotals(
            List<TemporalResponse> transactions,
            List<KPeriod> kPeriods
    ) {
        Map<KPeriod, Double> kTotals = new HashMap<>();

        for (KPeriod k : kPeriods) {
            double sum = 0;
            for (TemporalResponse t : transactions) {
                if (!t.getDate().isBefore(k.getStart()) && !t.getDate().isAfter(k.getEnd())) {
                    sum += t.getRemanent();
                }
            }
            kTotals.put(k, sum);
        }
        return kTotals;
    }
}
