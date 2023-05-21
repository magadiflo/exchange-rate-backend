package com.magadiflo.exchange.rate.app.services;

import com.magadiflo.exchange.rate.app.dto.ExchangeRateRecord;

import java.math.BigDecimal;
import java.util.Optional;

public interface IExchangeRateService {
    Optional<ExchangeRateRecord> getExchangeRate(Long baseId, Long quoteId);

    BigDecimal afterConversionById(Long baseId, Long quoteId, BigDecimal amount);

    BigDecimal afterConversionByIsoCode(String baseIsoCode, String quoteIsoCode, BigDecimal amount);
}
