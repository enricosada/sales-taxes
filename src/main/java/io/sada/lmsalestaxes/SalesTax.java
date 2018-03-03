package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public class SalesTax {
    public static final SalesTax NO_TAX = new SalesTax(0);

    private final int rate;

    public SalesTax(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public BigDecimal getAmountFor(final BigDecimal price) {
        if (this.getRate() == 0) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal("1.50");
        }
    }
}
