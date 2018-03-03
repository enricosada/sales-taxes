package io.sada.lmsalestaxes;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class AcceptanceTest {

    private MyApp createApp() {
        Map<String,SalesTax> rates = new HashMap<>();
        rates.put("music CD", new SalesTax(10));

        ITaxRatesForProduct taxRates = product -> rates.getOrDefault(product.getProduct(), SalesTax.NO_TAX);

        ProductStore productStore = new ProductStoreInMemory(new TestDataProductCategories().create());
        return new MyApp(new TaxCalculator(taxRates), productStore);
    }

    @Test
    public void scenario1() {
        MyApp app = createApp();

        app.purchase(1, "book", "12.49");
        app.purchase(1, "music CD", "14.99");
        app.purchase(1, "chocolate bar", "0.85");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 book: 12.49",
                "1 music CD: 16.49",
                "1 chocolate bar: 0.85",
                "Sales Taxes: 1.50",
                "Total: 29.83"
        }, receipt);
    }
}
