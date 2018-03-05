package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.receipt.ReceiptPrinter;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CashRegisterScreenAsLines implements CashRegisterScreen {

    private ArrayList<String> lines;

    public CashRegisterScreenAsLines() {
        this.lines = new ArrayList<>();
    }

    public void show(String[] lines) {
        Stream.of(lines).forEach(line -> this.lines.add(line));
    }

    public String[] getLines() {
        return lines.stream().toArray(String[]::new);
    }
}
