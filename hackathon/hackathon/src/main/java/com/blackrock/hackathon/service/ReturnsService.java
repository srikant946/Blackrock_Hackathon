package com.blackrock.hackathon.service;

import com.blackrock.hackathon.model.dto.*;
import com.blackrock.hackathon.model.entity.KPeriod;
import com.blackrock.hackathon.util.FinancialCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnsService {

    public ReturnsResponse calculateNPSReturns(ReturnsRequest request) {
        return calculateReturns(request, 0.0711, true);
    }

    public ReturnsResponse calculateIndexReturns(ReturnsRequest request) {
        return calculateReturns(request, 0.1449, false);
    }

    private ReturnsResponse calculateReturns(ReturnsRequest request, double rate, boolean isNPS) {
        ReturnsResponse response = new ReturnsResponse();
        List<SavingsByDate> savingsList = new ArrayList<>();

        double totalAmount = 0;
        double totalCeiling = 0;
        int yearsToInvest = 60 - request.getAge();

        // Sum up total invested and ceiling
        for (TemporalResponse t : request.getTransactions()) {
            totalAmount += t.getRemanent();
            totalCeiling += t.getCeiling();
        }

        // Process each k-period
        for (KPeriod kPeriod : request.getK()) {
            double sum = 0;
            for (TemporalResponse t : request.getTransactions())
            {
                if (!t.getDate().isBefore(kPeriod.getStart()) && !t.getDate().isAfter(kPeriod.getEnd())) {
                    sum += t.getRemanent();
                }
            }

            double futureValue = FinancialCalculator.compoundInterest(sum, rate, yearsToInvest);
            double adjustedValue = FinancialCalculator.adjustForInflation(futureValue, request.getInflation(), yearsToInvest);

            double taxBenefit = 0;
            if (isNPS) {
                taxBenefit = FinancialCalculator.calculateTaxBenefit(request.getWage() * 12, sum);
            }

            SavingsByDate savings = new SavingsByDate();
            savings.setStart(kPeriod.getStart());
            savings.setEnd(kPeriod.getEnd());
            savings.setAmount(sum);
            savings.setProfits(adjustedValue - sum);
            savings.setTaxBenefit(taxBenefit);

            savingsList.add(savings);
        }

        response.setTransactionsTotalAmount(totalAmount);
        response.setTotalCeiling(totalCeiling);
        response.setSavingsByDates(savingsList);

        return response;
    }
}