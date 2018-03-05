package io.sada.lmsalestaxes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReceiptPrinter {

    public String[] generate(List<ReceiptItem> orderLines, BigDecimal totalPrice, BigDecimal totalSalesTaxes) {

        List<String> itemLines =
                orderLines
                        .stream()
                        .map(item -> {
                            return "1 " + item.getDisplayName() + ": " + item.getItemPrice().toString();
                        })
                        .collect(Collectors.toList());

        String[] totals = {
                "Sales Taxes: " + totalSalesTaxes.toString(),
                "Total: " + totalPrice.toString() };

        return Stream.concat(itemLines.stream(), Stream.of(totals)).toArray(String[]::new);
    }
}
