package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public final class ReceiptItem {
    private final OrderItem item;
    private final String displayName;
    private final BigDecimal itemPrice;
    private final BigDecimal salesTaxes;

    public ReceiptItem(OrderItem item, String displayName, BigDecimal itemPrice, BigDecimal salesTaxes) {

        this.item = item;
        this.displayName = displayName;
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

    public String getDisplayName() { return displayName; }
}
