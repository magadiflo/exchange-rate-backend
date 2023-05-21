package com.magadiflo.exchange.rate.app.controllers;

import com.magadiflo.exchange.rate.app.services.IExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/exchange-rates")
public class ExchangeRateController {

    private final IExchangeRateService exchangeRateService;

    @GetMapping(path = "/base/{baseId}/quote/{quoteId}/amount/{amount}")
    public ResponseEntity<BigDecimal> performConversion(@PathVariable Long baseId, @PathVariable Long quoteId, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(this.exchangeRateService.afterConversionById(baseId, quoteId, amount));
    }

    @GetMapping(path = "/iso-codes/base/{baseIsoCode}/quote/{quoteIsoCode}/amount/{amount}")
    public ResponseEntity<BigDecimal> performConversion(@PathVariable String baseIsoCode, @PathVariable String quoteIsoCode, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(this.exchangeRateService.afterConversionByIsoCode(baseIsoCode, quoteIsoCode, amount));
    }

}
