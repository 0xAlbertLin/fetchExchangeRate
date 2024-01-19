package com.example.fetchexchangerate.repositories;

import com.example.fetchexchangerate.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, UUID>, JpaSpecificationExecutor<ExchangeRateEntity> {
    List<ExchangeRateEntity> findByCurrencyPairAndTimestampBetween(
            String currency, LocalDateTime startDate, LocalDateTime endDate
    );

}