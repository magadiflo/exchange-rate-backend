package com.magadiflo.exchange.rate.app.services;

import com.magadiflo.exchange.rate.app.entities.Currency;

import java.util.List;
import java.util.Optional;

public interface ICurrencyService {
    List<Currency> getAllCurrencies();

    Optional<Currency> findCurrencyById(Long id);

    Optional<Currency> findCurrencyByIsoCode(String isoCode);
}
