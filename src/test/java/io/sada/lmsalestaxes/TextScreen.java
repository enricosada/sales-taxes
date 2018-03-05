package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.receipt.Receipt;
import io.sada.lmsalestaxes.receipt.ReceiptTextFormatter;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TextScreen implements CashRegisterScreen {

    private final ReceiptTextFormatter receiptFormatter;
    private final ArrayList<String> lines;

    public TextScreen(ReceiptTextFormatter receiptFormatter) {
        this.receiptFormatter = receiptFormatter;
        this.lines = new ArrayList<>();
    }

    public void show(Receipt receipt) {
        String[] lines = receiptFormatter.print(receipt);
        Stream.of(lines).forEach(line -> this.lines.add(line));
    }

    public String[] getLines() {
        return lines.stream().toArray(String[]::new);
    }
}
