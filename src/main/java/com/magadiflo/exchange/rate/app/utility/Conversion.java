package com.magadiflo.exchange.rate.app.utility;

import java.math.BigDecimal;

public class Conversion {
    public static BigDecimal calculateConversion(BigDecimal amount, BigDecimal conversion) {
        return amount.multiply(conversion);
    }
}
