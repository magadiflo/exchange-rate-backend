package com.magadiflo.exchange.rate.app.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "exchange_rates", uniqueConstraints = {
        @UniqueConstraint(name = "uk_base_quote", columnNames = {"base_id", "quote_id"})
})
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversion", precision = 19, scale = 10)
    private BigDecimal conversion;

    @ManyToOne
    @JoinColumn(name = "base_id")
    private Currency base;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Currency quote;
}
