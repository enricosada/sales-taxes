package io.sada.lmsalestaxes.receipt;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private final List<ReceiptItem> orderLines;
    private final BigDecimal totalPrice;
    private final BigDecimal totalSalesTaxes;

    public Receipt(List<ReceiptItem> orderLines, BigDecimal totalPrice, BigDecimal totalSalesTaxes) {
        this.orderLines = orderLines;
        this.totalPrice = totalPrice;
        this.totalSalesTaxes = totalSalesTaxes;
    }

    public ReceiptItem[] getOrderLines() {
        return orderLines.stream().toArray(ReceiptItem[]::new);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalSalesTaxes() {
        return totalSalesTaxes;
    }
}
