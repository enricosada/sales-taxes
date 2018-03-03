package io.sada.lmsalestaxes.tax;

import io.sada.lmsalestaxes.OrderItem;

public interface ITaxRatesForProduct {
    SalesTax getForProduct(OrderItem product);
}
