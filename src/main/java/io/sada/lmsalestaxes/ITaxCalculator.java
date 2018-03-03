package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public interface ITaxCalculator {
    BigDecimal getSalesTaxes(OrderItem product, BigDecimal price);
}
