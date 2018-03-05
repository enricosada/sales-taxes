package io.sada.lmsalestaxes.receipt;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptPrinter {

    public Receipt generate(List<ReceiptItem> orderLines, BigDecimal totalPrice, BigDecimal totalSalesTaxes) {
        return new Receipt(orderLines, totalPrice, totalSalesTaxes);
    }
}

