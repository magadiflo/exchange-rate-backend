package com.magadiflo.exchange.rate.app.services.impl;

import com.magadiflo.exchange.rate.app.repositories.IExchangeRateRepository;
import com.magadiflo.exchange.rate.app.services.IExchangeRateService;
import com.magadiflo.exchange.rate.app.utility.Conversion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class ExchangeRateService implements IExchangeRateService {

    private final IExchangeRateRepository exchangeRateRepository;

    @Override
    @Transactional(readOnly = true)
    public BigDecimal afterConversionById(Long baseId, Long quoteId, BigDecimal amount) {
        return this.exchangeRateRepository.findByBaseIdAndQuoteId(baseId, quoteId)
                .map(conversion -> Conversion.calculateConversion(amount, conversion))
                .orElseGet(() -> this.exchangeRateRepository.findByBaseIdAndQuoteId(quoteId, baseId)
                        .map(conversion -> Conversion.calculateConversionReverse(amount, conversion))
                        .orElseGet(() -> Conversion.THERE_ARE_NOT_CONVERSION));
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal afterConversionByIsoCode(String baseIsoCode, String quoteIsoCode, BigDecimal amount) {
        return this.exchangeRateRepository.findByBaseIsoCodeAndQuoteIsoCode(baseIsoCode, quoteIsoCode)
                .map(conversion -> Conversion.calculateConversion(amount, conversion))
                .orElseGet(() -> this.exchangeRateRepository.findByBaseIsoCodeAndQuoteIsoCode(quoteIsoCode, baseIsoCode)
                        .map(conversion -> Conversion.calculateConversionReverse(amount, conversion))
                        .orElseGet(() -> Conversion.THERE_ARE_NOT_CONVERSION));

    }

}
