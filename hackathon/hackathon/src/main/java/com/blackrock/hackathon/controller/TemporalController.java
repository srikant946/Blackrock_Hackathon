package com.blackrock.hackathon.controller;

import com.blackrock.hackathon.model.dto.FilterRequest;
import com.blackrock.hackathon.model.dto.TemporalResponse;
import com.blackrock.hackathon.service.TemporalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blackrock/challenge/v1/transactions/filter")
public class TemporalController {

    @Autowired
    private TemporalService temporalService;

    @PostMapping
    public Map<String, List<TemporalResponse>> filterTransactions(
            @RequestBody FilterRequest filterRequest
    ) {
        // Transactions are applied the Q and P Periods. K Period values are just fetched for inKPeriod value later
        List<TemporalResponse> updatedTransactions = temporalService.applyQandPPeriods(
                filterRequest.getTransactions(),
                filterRequest.getQ(),
                filterRequest.getP(),
                filterRequest.getK()
        );

        // Transaction Validation (Valid/Invalid/Duplicates)
        Map<String, List<TemporalResponse>> validationResults = temporalService.validateTransactions(updatedTransactions, filterRequest.getWage());

        // Returning transaction list sorted by validation
        return validationResults;
    }
}