package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public final class OrderItem {
    private final int quantity;
    private final String product;
    private final BigDecimal unitaryPrice;

    public OrderItem(int quantity, String product, BigDecimal unitaryPrice) {
        this.quantity = quantity;
        this.product = product;
        this.unitaryPrice = unitaryPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }
}
