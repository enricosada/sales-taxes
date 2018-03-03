package io.sada.lmsalestaxes;

public interface ITaxRatesForProduct {
    SalesTax[] getForProduct(OrderItem product);
}
