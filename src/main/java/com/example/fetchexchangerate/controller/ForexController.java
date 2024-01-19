package com.example.fetchexchangerate.controller;

import com.example.fetchexchangerate.dto.BaseResponse;
import com.example.fetchexchangerate.dto.ForexRequest;
import com.example.fetchexchangerate.dto.ForexResponse;
import com.example.fetchexchangerate.services.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api2")
public class ForexController {
    @Autowired
    private ForexService forexService;

    @PostMapping("/forex")
    public ResponseEntity<BaseResponse<List<ForexResponse>>> getExchangeRates(
            @RequestBody ForexRequest request) {

        LocalDate startDate = LocalDate.parse(request.getStartDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate endDate = LocalDate.parse(request.getEndDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String currencyPair = request.getCurrency().toUpperCase() + "/NTD";

        BaseResponse<List<ForexResponse>> response = forexService.getExchangeRates(startDate, endDate, currencyPair);
        return ResponseEntity.ok(response);
    }
}
