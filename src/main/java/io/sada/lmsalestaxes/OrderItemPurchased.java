package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public final class OrderItemPurchased {
    private final OrderItem item;
    private final BigDecimal itemPrice;
    private final BigDecimal salesTaxes;

    public OrderItemPurchased(OrderItem item, BigDecimal itemPrice, BigDecimal salesTaxes) {

        this.item = item;
        this.itemPrice = itemPrice;
        this.salesTaxes = salesTaxes;
    }

    public OrderItem getItem() {
        return item;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }
}
