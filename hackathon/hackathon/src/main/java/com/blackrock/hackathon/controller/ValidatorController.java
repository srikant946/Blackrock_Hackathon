package com.blackrock.hackathon.controller;

import com.blackrock.hackathon.model.dto.TransactionRequest;
import com.blackrock.hackathon.service.ValidatorService;
import com.blackrock.hackathon.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blackrock/challenge/v1/transactions/validator")
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    // Accepting transaction list and validating it
    @PostMapping
    @JsonView(Views.TransactionValidatorView.class)
    public Map<String, List<?>> validateTransactions(
            @RequestBody TransactionRequest requestDTO
    ) {
        return validatorService.validateTransactions(requestDTO.getWage(), requestDTO.getTransactions());
    }
}
