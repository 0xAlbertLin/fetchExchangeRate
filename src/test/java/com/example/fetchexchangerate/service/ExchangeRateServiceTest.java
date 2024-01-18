package com.example.fetchexchangerate.service;

import com.example.fetchexchangerate.repositories.ExchangeRateRepository;
import com.example.fetchexchangerate.services.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {
    @Mock
    private ExchangeRateRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @Test
    public void getExchangeRateTest() throws Exception {
        String jsonResponse = "[{\"Date\":\"20231201\",\"USD/NTD\":\"31.475\"}]";
        ResponseEntity<String> mockResponse = ResponseEntity.ok(jsonResponse);
        lenient().when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockResponse);

        exchangeRateService.getExchangeRate();

        verify(repository).saveAll(any(List.class));
    }


}
