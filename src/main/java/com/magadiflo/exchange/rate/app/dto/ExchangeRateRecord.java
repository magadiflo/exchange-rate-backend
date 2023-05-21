package com.magadiflo.exchange.rate.app.dto;

import com.magadiflo.exchange.rate.app.entities.Currency;

import java.math.BigDecimal;

public record ExchangeRateRecord(Currency base, Currency quote, BigDecimal conversion) {
}
