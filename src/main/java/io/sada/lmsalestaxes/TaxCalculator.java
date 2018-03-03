package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public class TaxCalculator implements ITaxCalculator {

    private final ITaxRatesForProduct taxRates;

    public TaxCalculator(ITaxRatesForProduct taxRates) {
        this.taxRates = taxRates;
    }

    public BigDecimal getSalesTaxes(String product, BigDecimal price) {
        SalesTax taxRate = this.taxRates.getForProduct(product);
        return taxRate.getAmountFor(price);
    }
}

