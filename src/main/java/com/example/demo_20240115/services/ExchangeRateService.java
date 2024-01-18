package com.example.demo_20240115.services;

import com.example.demo_20240115.entities.ExchangeRateEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo_20240115.repositories.ExchangeRateRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateService {
    private final ExchangeRateRepository repository;

    String url = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
    public ExchangeRateService(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public void getExchangeRate() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> dataList = mapper.readValue(responseBody, new TypeReference<List<Map<String, String>>>() {});

        List<ExchangeRateEntity> exchangeRates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        for (Map<String, String> data : dataList) {
            LocalDate date = LocalDate.parse(data.get("Date"), formatter);
            data.forEach((key, value) -> {
                if (!"Date".equals(key)) {
                    ExchangeRateEntity rate = new ExchangeRateEntity();
                    rate.setTimestamp(date.atStartOfDay());
                    rate.setCurrencyPair(key);
                    rate.setRate(Double.parseDouble(value));
                    exchangeRates.add(rate);
                }
            });
        }
        repository.saveAll(exchangeRates);
    }
}
