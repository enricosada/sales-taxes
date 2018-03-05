package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.receipt.Receipt;

public interface CashRegisterScreen {

    void show(Receipt receipt);

    String[] getLines();
}
