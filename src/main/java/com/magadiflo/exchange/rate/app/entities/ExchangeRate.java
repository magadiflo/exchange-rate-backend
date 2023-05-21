package com.magadiflo.exchange.rate.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal conversion;

    @ManyToOne
    @JoinColumn(name = "base_id")
    private Currency base;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Currency quote;
}
