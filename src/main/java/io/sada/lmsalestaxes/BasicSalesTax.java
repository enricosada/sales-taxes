package io.sada.lmsalestaxes;

public class BasicSalesTax implements ITaxRatesForProduct {
    public final static SalesTax BASIC_TAX = new SalesTax(10);

    private final SalesTax defaultTax;

    public BasicSalesTax(SalesTax defaultTax) {
        this.defaultTax = defaultTax;
    }

    public SalesTax getForProduct(OrderItem product) {
        return defaultTax;
    }
}
