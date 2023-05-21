package com.magadiflo.exchange.rate.app.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iso_code", length = 3, unique = true)
    private String isoCode;

    private String name;
}
