package com.blackrock.hackathon.service;

import com.blackrock.hackathon.model.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ValidatorService {

    private static final double MAX_AMOUNT = 5e5;

    public Map<String, List<?>> validateTransactions(double wage, List<TransactionResponse> transactions) {
        List<TransactionResponse> valid = new ArrayList<>();
        List<TransactionResponse> invalid = new ArrayList<>();


        Set<String> seenTimestamps = new HashSet<>();

        for (TransactionResponse t : transactions) {
            String ts = t.getDate().toString();

            // Check duplicate
            if (seenTimestamps.contains(ts)) {
                invalid.add(new TransactionResponse(convertStrToLocalDateTime(t.getDate()), t.getAmount(), "Duplicate Transaction"));
                continue;
            } else {
                seenTimestamps.add(ts);
            }

            if (t.getAmount() < 0 || t.getAmount() > MAX_AMOUNT) {
                invalid.add(new TransactionResponse(convertStrToLocalDateTime(t.getDate()), t.getAmount(), "Amount exceeds allowed limit"));
            } else if (t.getRemanent() < 0 || t.getRemanent() > wage) {
                invalid.add(new TransactionResponse(convertStrToLocalDateTime(t.getDate()), t.getAmount(), "Amount exceeds allowed limit"));
            } else {
                valid.add(t);
            }
        }

        Map<String, List<?>> result = new HashMap<>();
        result.put("valid", valid);
        result.put("invalid", invalid);
        return result;
    }

    public LocalDateTime convertStrToLocalDateTime(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Convert String to LocalDateTime
        LocalDateTime convertedDate = LocalDateTime.parse(date, formatter);

        return convertedDate;
    }
}
