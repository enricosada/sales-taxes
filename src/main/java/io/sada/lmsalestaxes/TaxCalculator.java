package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public class TaxCalculator implements ITaxCalculator {
    public BigDecimal getSalesTaxes(String product, BigDecimal price) {
        if ("book".equals(product)) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal("1.50");
        }
    }
}
