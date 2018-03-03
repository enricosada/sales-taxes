package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
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

    @Property
    public void oneItemAnyPriceIsAlwaysTheTotal(BigDecimal price) {
        MyApp app = new MyApp();

        app.purchase(1, "book", price);

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 book: " + price.toString(),
                "Sales Taxes: 0",
                "Total: " + price.toString()
        }, receipt);
    }

}
