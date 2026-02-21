package com.blackrock.hackathon.service;

import com.blackrock.hackathon.model.dto.ExpenseRequest;
import com.blackrock.hackathon.model.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService
{
    public List<TransactionResponse> calculateTransactions(List<ExpenseRequest> expenses) {
        List<TransactionResponse> result = new ArrayList<>();

        for (ExpenseRequest expense : expenses) {
            TransactionResponse t = new TransactionResponse();

            // Convert the expense date string to LocalDateTime
            String expenseDateStr = expense.getDate();

            t.setDate(expenseDateStr);

            // Process the expense data
            t.setAmount(expense.getAmount());

            // Ceiling & remanent logic
            double ceiling = Math.ceil(expense.getAmount() / 100.0) * 100;
            double remanent = ceiling - expense.getAmount();

            t.setCeiling(ceiling);
            t.setRemanent(remanent);

            // Add the transaction to the result
            result.add(t);
        }

        return result;
    }
}
