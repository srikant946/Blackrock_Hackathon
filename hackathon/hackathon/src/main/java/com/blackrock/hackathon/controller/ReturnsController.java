package com.blackrock.hackathon.controller;

import com.blackrock.hackathon.model.dto.ReturnsRequest;
import com.blackrock.hackathon.model.dto.ReturnsResponse;
import com.blackrock.hackathon.service.ReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1/transactions/")
public class ReturnsController {

    @Autowired
    private ReturnsService returnsService;

    // For NPS
    @PostMapping("/nps")
    public ReturnsResponse calculateNPS(@RequestBody ReturnsRequest request) {
        return returnsService.calculateNPSReturns(request);
    }

    // For Index
    @PostMapping("/index")
    public ReturnsResponse calculateIndex(@RequestBody ReturnsRequest request) {
        return returnsService.calculateIndexReturns(request);
    }
}
