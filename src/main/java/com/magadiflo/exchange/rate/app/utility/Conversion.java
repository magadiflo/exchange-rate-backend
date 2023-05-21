package com.magadiflo.exchange.rate.app.utility;

import java.math.BigDecimal;
import java.math.MathContext;

public class Conversion {
    public static final BigDecimal THERE_ARE_NOT_CONVERSION = new BigDecimal("-1");

    public static BigDecimal calculateConversion(BigDecimal amount, BigDecimal conversion) {
        return amount.multiply(conversion);
    }

    public static BigDecimal calculateConversionReverse(BigDecimal amount, BigDecimal conversion) {
        return amount.multiply(conversionReverse(conversion));
    }

    public static BigDecimal conversionReverse(BigDecimal conversion) {
        BigDecimal base = new BigDecimal("1");
        return base.divide(conversion, new MathContext(10));
    }
}
