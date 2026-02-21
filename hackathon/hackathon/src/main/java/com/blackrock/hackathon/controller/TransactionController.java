package com.blackrock.hackathon.controller;

import com.blackrock.hackathon.model.dto.ExpenseRequest;
import com.blackrock.hackathon.model.dto.TransactionResponse;
import com.blackrock.hackathon.service.TransactionService;
import com.blackrock.hackathon.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blackrock/challenge/v1/transactions/parse")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Entering list of transactions
    @PostMapping
    @JsonView(Views.TransactionBuilderView.class)
    public List<TransactionResponse> parseTransactions(@RequestBody List<ExpenseRequest> expenses) {
        return transactionService.calculateTransactions(expenses);
    }
}
