package com.example.demo_20240115.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_20240115.services.ExchangeRateService;

@RestController
@RequestMapping("/api")
public class RateController {

    @Autowired
    private final ExchangeRateService exchangeRateService;

    public RateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(value = "/rate")
    public void getRate() throws JsonProcessingException {
        exchangeRateService.getExchangeRate();
    }

}
