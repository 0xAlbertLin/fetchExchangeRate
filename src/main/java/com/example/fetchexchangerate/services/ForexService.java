package com.example.fetchexchangerate.services;

import com.example.fetchexchangerate.dto.BaseResponse;
import com.example.fetchexchangerate.dto.ErrorResponse;
import com.example.fetchexchangerate.dto.ForexResponse;
import com.example.fetchexchangerate.entities.ExchangeRateEntity;
import com.example.fetchexchangerate.repositories.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForexService {

    @Autowired
    private final ExchangeRateRepository repository;

    public ForexService(ExchangeRateRepository repository) {
        this.repository = repository;
    }

    public BaseResponse<List<ForexResponse>> getExchangeRates(LocalDate startDate, LocalDate endDate, String currencyPair) {
        // 驗證日期區間是否符合規定
        if (startDate.isAfter(endDate)
                || startDate.isBefore(LocalDate.now().minusYears(1))
                || endDate.isAfter(LocalDate.now().minusDays(1))) {
            return new BaseResponse<>(new ErrorResponse("E001", "日期區間不符"), null);
        }


        List<ExchangeRateEntity> entities = repository.findByCurrencyPairAndTimestampBetween(
                currencyPair, startDate.atStartOfDay(), endDate.atTime(23, 59, 59));

        List<ForexResponse> responseData = new ArrayList<>();

        // 假設 currencyPair 已經是 "USD/TWD" 或您只處理這種情況
        for (ExchangeRateEntity entity : entities) {
            ForexResponse forexResponse = new ForexResponse(
                    entity.getTimestamp().toLocalDate().toString(),
                    entity.getRate()
            );
            responseData.add(forexResponse);
        }

        return new BaseResponse<>(new ErrorResponse("0000", "成功"), responseData);
    }


}
