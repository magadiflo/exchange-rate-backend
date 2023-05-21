package com.magadiflo.exchange.rate.app.services;

import java.math.BigDecimal;

public interface IExchangeRateService {
    BigDecimal afterConversionById(Long baseId, Long quoteId, BigDecimal amount);
    BigDecimal afterConversionByIsoCode(String baseIsoCode, String quoteIsoCode, BigDecimal amount);
}
