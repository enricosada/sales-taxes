package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public final class ReceiptItem {
    private final String displayName;
    private final BigDecimal itemPrice;
    private final BigDecimal salesTaxes;

    public ReceiptItem(String displayName, BigDecimal itemPrice, BigDecimal salesTaxes) {

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
}
