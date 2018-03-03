package io.sada.lmsalestaxes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
            BigDecimal amount = new BigDecimal(rate).multiply(price).divide(new BigDecimal(100));
            return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }
}
