package com.example.fetchexchangerate.tasks;

import com.example.fetchexchangerate.services.ExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final ExchangeRateService service;

    public ScheduledTasks(ExchangeRateService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 0 18 * * ?")
    public void fetchDailyExchangeRate() throws JsonProcessingException {
        service.getExchangeRate();
    }
}
