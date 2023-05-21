package com.magadiflo.exchange.rate.app.services.impl;

import com.magadiflo.exchange.rate.app.entities.Currency;
import com.magadiflo.exchange.rate.app.repositories.ICurrencyRepository;
import com.magadiflo.exchange.rate.app.services.ICurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository currencyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Currency> getAllCurrencies() {
        return this.currencyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> findCurrencyById(Long id) {
        return this.currencyRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> findCurrencyByIsoCode(String isoCode) {
        return this.currencyRepository.findByIsoCode(isoCode);
    }
}
