package io.sada.lmsalestaxes.receipt;

import java.math.BigDecimal;

public final class ReceiptItem {
    private final int quantity;
    private final String displayName;
    private final BigDecimal itemPrice;
    private final BigDecimal salesTaxes;

    public ReceiptItem(int quantity, String displayName, BigDecimal itemPrice, BigDecimal salesTaxes) {
        this.quantity = quantity;

        this.displayName = displayName;
        this.itemPrice = itemPrice;
        this.salesTaxes = salesTaxes;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public String getDisplayName() { return displayName; }

    public int getQuantity() { return quantity; }
}
