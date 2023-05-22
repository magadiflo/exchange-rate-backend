package com.magadiflo.exchange.rate.app.repositories;

import com.magadiflo.exchange.rate.app.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface IExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query(value = "SELECT er FROM ExchangeRate AS er WHERE er.base.id = ?1 AND er.quote.id = ?2")
    Optional<ExchangeRate> findExchangeRate(Long baseId, Long quoteId);

    @Query(value = "SELECT er.conversion FROM ExchangeRate AS er WHERE er.base.id = ?1 AND er.quote.id = ?2")
    Optional<BigDecimal> findByBaseIdAndQuoteId(Long baseId, Long quoteId);

    @Query(value = "SELECT er.conversion FROM ExchangeRate AS er WHERE er.base.isoCode = ?1 AND er.quote.isoCode = ?2")
    Optional<BigDecimal> findByBaseIsoCodeAndQuoteIsoCode(String baseIsoCode, String quoteIsoCode);

    @Query(value = "SELECT COUNT(er) > 0 FROM ExchangeRate AS er WHERE er.base.id = ?1 AND er.quote.id = ?2")
    Boolean existExchangeRate(Long baseId, Long quoteId);

    @Query(value = "SELECT COUNT(er) > 0 FROM ExchangeRate AS er WHERE er.base.id = ?1 AND er.quote.id = ?2")
    Boolean existExchangeRateInReverse(Long quoteId, Long baseId);
}
