package io.sada.lmsalestaxes;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScenarioTest {

    @Test
    public void oneItem() {
        MyApp app = new MyApp();

        app.purchase(1, "book", "12.49");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 book: 12.49",
                "Sales Taxes: 0",
                "Total: 12.49"
        }, receipt);
    }
}
