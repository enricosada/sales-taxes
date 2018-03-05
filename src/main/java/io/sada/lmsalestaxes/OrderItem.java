package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.store.ProductCategory;

import java.math.BigDecimal;

public final class OrderItem {
    private final int quantity;
    private final String product;
    private final BigDecimal unitaryPrice;
    private boolean isImported;
    private ProductCategory category;

    public OrderItem(int quantity, String product, BigDecimal unitaryPrice, boolean isImported, ProductCategory category) {
        this.quantity = quantity;
        this.product = product;
        this.unitaryPrice = unitaryPrice;
        this.isImported = isImported;
        this.category = category;
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

    public ProductCategory getCategory() { return category; }
}
