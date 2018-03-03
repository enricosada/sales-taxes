package io.sada.lmsalestaxes.tax;

import io.sada.lmsalestaxes.OrderItem;

import java.math.BigDecimal;

public interface ITaxCalculator {
    BigDecimal getSalesTaxes(OrderItem product, BigDecimal price);
}
