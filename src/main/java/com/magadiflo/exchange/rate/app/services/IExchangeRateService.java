package com.magadiflo.exchange.rate.app.services;

import com.magadiflo.exchange.rate.app.dto.ExchangeRateRecord;
import com.magadiflo.exchange.rate.app.entities.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IExchangeRateService {
    List<ExchangeRate> getAllExchangeRates();

    Optional<ExchangeRateRecord> getExchangeRate(Long baseId, Long quoteId);

    BigDecimal afterConversionById(Long baseId, Long quoteId, BigDecimal amount);

    BigDecimal afterConversionByIsoCode(String baseIsoCode, String quoteIsoCode, BigDecimal amount);

    Long saveExchangeRate(ExchangeRate exchangeRate);

    Optional<ExchangeRate> updateExchangeRate(Long id, ExchangeRate exchangeRate);
}
