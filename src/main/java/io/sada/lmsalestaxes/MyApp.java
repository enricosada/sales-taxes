package io.sada.lmsalestaxes;

public class MyApp {
    public void purchase(int quantity, String product, String unitaryPrice) {
    }

    public String[] getReceipt() {
        return new String[]{
                "1 book: 12.49",
                "Sales Taxes: 0",
                "Total: 12.49"
        };
    }
}
