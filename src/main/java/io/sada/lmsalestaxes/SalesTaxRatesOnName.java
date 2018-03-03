package io.sada.lmsalestaxes;

import java.util.HashMap;
import java.util.Map;

public class SalesTaxRatesOnName {
    private final HashMap<String, SalesTax> rates;
    private SalesTax defaultTax;

    public SalesTaxRatesOnName(SalesTax defaultTax) {
        rates = new HashMap<>();
        this.defaultTax = defaultTax;
    }

    public SalesTaxRatesOnName withProduct(String productName, SalesTax salesTax) {
        rates.put(productName, salesTax);
        return this;
    }

    public ITaxRatesForProduct create() {
        Map<String,SalesTax> r = new HashMap(rates);
        ITaxRatesForProduct taxRates = product -> {
            SalesTax tax = r.getOrDefault(product.getProduct(), defaultTax);
            return new SalesTax[] { tax };
        };
        return taxRates;
    }
}
