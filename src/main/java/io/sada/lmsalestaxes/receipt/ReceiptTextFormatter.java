package io.sada.lmsalestaxes.receipt;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReceiptTextFormatter {
    public String[] print(Receipt receipt) {
        List<String> itemLines =
                        Stream.of(receipt.getOrderLines())
                        .map(item -> {
                            return item.getQuantity() + " " + item.getDisplayName() + ": " + item.getItemPrice().toString();
                        })
                        .collect(Collectors.toList());

        String[] totals = {
                "Sales Taxes: " + receipt.getTotalSalesTaxes().toString(),
                "Total: " + receipt.getTotalPrice().toString() };

        return Stream.concat(itemLines.stream(), Stream.of(totals)).toArray(String[]::new);
    }
}
