package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class ScenarioTest {

    private MyApp createApp() {
        Map<String,SalesTax> rates = new HashMap<>();
        rates.put("music CD", new SalesTax(10));

        ITaxRatesForProduct taxRates = product -> rates.getOrDefault(product.getProduct(), SalesTax.NO_TAX);

        ProductStore productStore = new ProductStoreInMemory(new TestDataProductCategories().create());
        return new MyApp(new TaxCalculator(taxRates), productStore);
    }

    @Test
    public void oneItem() {
        MyApp app = createApp();

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
        MyApp app = createApp();

        app.purchase(1, "book", price);

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 book: " + price.toString(),
                "Sales Taxes: 0",
                "Total: " + price.toString()
        }, receipt);
    }

    @Test
    public void oneTaxedItem() {
        MyApp app = createApp();

        app.purchase(1, "music CD", "14.99");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 music CD: 16.49",
                "Sales Taxes: 1.50",
                "Total: 16.49"
        }, receipt);
    }

    @Test
    public void oneImportedItem() {
        MyApp app = createApp();

        app.purchase(1, "imported book", "10.00");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 imported book: 10.00",
                "Sales Taxes: 0",
                "Total: 10.00"
        }, receipt);
    }

    @Property
    public void oneTaxedItemShouldApplyTaxesForTotal(BigDecimal price) {
        ITaxCalculator foo = (_p, m) -> m.add(BigDecimal.ONE);
        MyApp app = new MyApp(foo, new ProductStoreInMemory(new HashMap<>()));

        app.purchase(1, "music CD", price);

        String[] receipt = app.getReceipt();

        BigDecimal taxes = price.add(BigDecimal.ONE);
        BigDecimal itemPrice = price.add(taxes);

        assertArrayEquals(new String[]{
                "1 music CD: " + itemPrice.toString(),
                "Sales Taxes: " + taxes.toString(),
                "Total: " + itemPrice.toString()
        }, receipt);
    }
}
