package io.sada.lmsalestaxes;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class TaxCalculator implements ITaxCalculator {

    private final ITaxRatesForProduct[] taxRates;

    public TaxCalculator(ITaxRatesForProduct... taxRates) {
        this.taxRates = taxRates;
    }

    public BigDecimal getSalesTaxes(OrderItem product, BigDecimal price) {
        return Stream.of(this.taxRates)
                .map(taxRate -> taxRate.getForProduct(product))
                .map(taxRate -> taxRate.getAmountFor(price))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

