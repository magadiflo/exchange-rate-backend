package com.magadiflo.exchange.rate.app.repositories;

import com.magadiflo.exchange.rate.app.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByIsoCode(String isoCode);
}
