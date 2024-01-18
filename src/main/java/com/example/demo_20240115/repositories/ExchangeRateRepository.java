package com.example.demo_20240115.repositories;

import com.example.demo_20240115.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, UUID>, JpaSpecificationExecutor<ExchangeRateEntity> {

}