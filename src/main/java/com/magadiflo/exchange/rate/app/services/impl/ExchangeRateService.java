package com.magadiflo.exchange.rate.app.services.impl;

import com.magadiflo.exchange.rate.app.dto.ExchangeRateRecord;
import com.magadiflo.exchange.rate.app.entities.ExchangeRate;
import com.magadiflo.exchange.rate.app.repositories.IExchangeRateRepository;
import com.magadiflo.exchange.rate.app.services.IExchangeRateService;
import com.magadiflo.exchange.rate.app.utility.Conversion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExchangeRateService implements IExchangeRateService {

    private final IExchangeRateRepository exchangeRateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRate> getAllExchangeRates() {
        return this.exchangeRateRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExchangeRateRecord> getExchangeRate(Long baseId, Long quoteId) {
        return Optional.ofNullable(this.exchangeRateRepository.findExchangeRate(baseId, quoteId)
                .map(exchangeRate -> new ExchangeRateRecord(exchangeRate.getBase(), exchangeRate.getQuote(), exchangeRate.getConversion()))
                .orElseGet(() -> this.exchangeRateRepository.findExchangeRate(quoteId, baseId)
                        .map(exchangeRate -> {
                            BigDecimal conversionReverse = Conversion.conversionReverse(exchangeRate.getConversion());
                            return new ExchangeRateRecord(exchangeRate.getQuote(), exchangeRate.getBase(), conversionReverse);
                        })
                        .orElseGet(() -> null)));
    }

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

    @Override
    @Transactional
    public Long saveExchangeRate(ExchangeRate exchangeRate) {
        Boolean existDirect = this.exchangeRateRepository.existExchangeRate(exchangeRate.getBase().getId(), exchangeRate.getQuote().getId());
        Boolean existReverse = this.exchangeRateRepository.existExchangeRateInReverse(exchangeRate.getQuote().getId(), exchangeRate.getBase().getId());

        if (!existDirect && !existReverse) {
            return this.exchangeRateRepository.save(exchangeRate).getId();
        }

        if (existDirect) {
            throw new RuntimeException("El tipo de cambio ya está registrado");
        }

        throw new RuntimeException("El tipo de cambio está registrado de manera inversa. Si quiere registrar este tipo de cambio, primero debe eliminar el tipo de cambio inverso");
    }

    @Override
    @Transactional
    public Optional<ExchangeRate> updateExchangeRate(Long id, ExchangeRate exchangeRate) {
        return this.exchangeRateRepository.findById(id)
                .map(exchangeRateDB -> {
                    Optional<ExchangeRate> optional = this.exchangeRateRepository.findExchangeRateDirectOrReverse(exchangeRate.getBase().getId(), exchangeRate.getQuote().getId());

                    if (optional.isEmpty() || exchangeRateDB.getBase().getId().equals(exchangeRate.getBase().getId())
                            && exchangeRateDB.getQuote().getId().equals(exchangeRate.getQuote().getId())) {

                        exchangeRateDB.setConversion(exchangeRate.getConversion());
                        exchangeRateDB.setBase(exchangeRate.getBase());
                        exchangeRateDB.setQuote(exchangeRate.getQuote());

                        return Optional.of(this.exchangeRateRepository.save(exchangeRateDB));
                    }

                    throw new RuntimeException("No puede actualizar. El tipo de cambio ya existe (directa o inversa)");

                })
                .orElseGet(Optional::empty);
    }

}
