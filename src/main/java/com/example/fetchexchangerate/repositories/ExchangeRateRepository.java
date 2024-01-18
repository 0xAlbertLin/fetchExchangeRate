package com.example.fetchexchangerate.repositories;

import com.example.fetchexchangerate.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, UUID>, JpaSpecificationExecutor<ExchangeRateEntity> {

}