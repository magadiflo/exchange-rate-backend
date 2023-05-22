package com.magadiflo.exchange.rate.app.controllers;

import com.magadiflo.exchange.rate.app.dto.ExchangeRateRecord;
import com.magadiflo.exchange.rate.app.entities.ExchangeRate;
import com.magadiflo.exchange.rate.app.services.IExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/exchange-rates")
public class ExchangeRateController {

    private final IExchangeRateService exchangeRateService;

    @GetMapping
    public ResponseEntity<List<ExchangeRate>> getAllExchangeRates() {
        return ResponseEntity.ok(this.exchangeRateService.getAllExchangeRates());
    }

    @GetMapping(path = "/base/{baseId}/quote/{quoteId}")
    public ResponseEntity<ExchangeRateRecord> getExchangeRate(@PathVariable Long baseId, @PathVariable Long quoteId) {
        return this.exchangeRateService.getExchangeRate(baseId, quoteId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(path = "/base/{baseId}/quote/{quoteId}/amount/{amount}")
    public ResponseEntity<BigDecimal> performConversion(@PathVariable Long baseId, @PathVariable Long quoteId, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(this.exchangeRateService.afterConversionById(baseId, quoteId, amount));
    }

    @GetMapping(path = "/iso-codes/base/{baseIsoCode}/quote/{quoteIsoCode}/amount/{amount}")
    public ResponseEntity<BigDecimal> performConversion(@PathVariable String baseIsoCode, @PathVariable String quoteIsoCode, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(this.exchangeRateService.afterConversionByIsoCode(baseIsoCode, quoteIsoCode, amount));
    }

    @PostMapping
    public ResponseEntity<Long> saveExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.exchangeRateService.saveExchangeRate(exchangeRate));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable Long id, @RequestBody ExchangeRate exchangeRate) {
        return this.exchangeRateService.updateExchangeRate(id, exchangeRate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
