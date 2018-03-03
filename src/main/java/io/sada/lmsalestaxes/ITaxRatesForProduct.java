package io.sada.lmsalestaxes;

public interface ITaxRatesForProduct {
    SalesTax getForProduct(String product);
}
