package io.sada.lmsalestaxes;

import java.util.stream.Stream;

public class BasicSalesTax implements ITaxRatesForProduct {
    public final static SalesTax BASIC_TAX = new SalesTax(10);

    private final SalesTax defaultTax;
    private final ProductCategory[] exemptions;

    public BasicSalesTax(SalesTax defaultTax, ProductCategory... exemptions) {
        this.defaultTax = defaultTax;
        this.exemptions = exemptions;
    }

    public SalesTax getForProduct(OrderItem product) {
        ProductCategory cat = product.getCategory();
        if (Stream.of(exemptions).filter(x -> x.equals(cat)).findAny().isPresent()) {
            return SalesTax.NO_TAX;
        }

        return defaultTax;
    }
}
