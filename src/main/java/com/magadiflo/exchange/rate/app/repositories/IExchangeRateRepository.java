package com.magadiflo.exchange.rate.app.repositories;

import com.magadiflo.exchange.rate.app.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
}
