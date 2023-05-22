package com.magadiflo.exchange.rate.app.controllers;

import com.magadiflo.exchange.rate.app.entities.Currency;
import com.magadiflo.exchange.rate.app.services.ICurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/currencies")
public class CurrencyController {

    private final ICurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return ResponseEntity.ok(this.currencyService.getAllCurrencies());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Long id) {
        return this.currencyService.findCurrencyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(path = "/iso-code/{isoCode}")
    public ResponseEntity<Currency> getCurrencyByIsoCode(@PathVariable String isoCode) {
        return this.currencyService.findCurrencyByIsoCode(isoCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
