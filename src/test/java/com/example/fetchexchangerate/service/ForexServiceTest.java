package com.example.fetchexchangerate;

import com.example.fetchexchangerate.dto.BaseResponse;
import com.example.fetchexchangerate.dto.ForexResponse;
import com.example.fetchexchangerate.entities.ExchangeRateEntity;
import com.example.fetchexchangerate.repositories.ExchangeRateRepository;
import com.example.fetchexchangerate.services.ForexService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ForexServiceTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ForexService forexService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExchangeRates_Success() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now().minusDays(1);
        String currencyPair = "USD/TWD";

        ExchangeRateEntity mockEntity = new ExchangeRateEntity();
        mockEntity.setCurrencyPair(currencyPair);
        mockEntity.setRate(31.01);
        mockEntity.setTimestamp(LocalDateTime.now().minusDays(2));

        when(exchangeRateRepository.findByCurrencyPairAndTimestampBetween(currencyPair, startDate.atStartOfDay(), endDate.atTime(23, 59, 59)))
                .thenReturn(Collections.singletonList(mockEntity));

        BaseResponse<List<ForexResponse>> response = forexService.getExchangeRates(startDate, endDate, currencyPair);

        assertNotNull(response);
        assertEquals("0000", response.getError().getCode());
        assertFalse(response.getCurrency().isEmpty());
        assertEquals(31.01, response.getCurrency().get(0).getUsd());
    }

    @Test
    public void testGetExchangeRates_DateRangeError() {
        LocalDate startDate = LocalDate.now().minusYears(2);
        LocalDate endDate = LocalDate.now();
        String currencyPair = "USD/TWD";

        BaseResponse<List<ForexResponse>> response = forexService.getExchangeRates(startDate, endDate, currencyPair);

        assertNotNull(response);
        assertEquals("E001", response.getError().getCode());
        assertNull(response.getCurrency());
    }
}
