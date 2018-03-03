package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public final class OrderItem {
    private final int quantity;
    private final String product;
    private final BigDecimal unitaryPrice;
    private boolean isImported;

    public OrderItem(int quantity, String product, BigDecimal unitaryPrice, boolean isImported) {
        this.quantity = quantity;
        this.product = product;
        this.unitaryPrice = unitaryPrice;
        this.isImported = isImported;
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

    public boolean getIsImported() { return isImported; }
}
