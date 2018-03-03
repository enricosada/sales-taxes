package io.sada.lmsalestaxes.tax;

import java.math.BigDecimal;
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
            return applyRounding(amount);
        }
    }

    private BigDecimal applyRounding(BigDecimal amount) {
        BigDecimal result = new BigDecimal(Math.ceil(amount.doubleValue() * 20) / 20);
        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
