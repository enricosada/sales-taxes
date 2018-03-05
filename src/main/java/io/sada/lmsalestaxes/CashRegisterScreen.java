package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.receipt.ReceiptPrinter;

public interface CashRegisterScreen {

    void show(String[] receipt);

    String[] getLines();
}
