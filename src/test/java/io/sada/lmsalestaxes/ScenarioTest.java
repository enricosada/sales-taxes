package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.sada.lmsalestaxes.tax.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class ScenarioTest {

    private CashRegister createApp() {

        ITaxRatesForProduct taxRates =
                new SalesTaxRatesOnName(SalesTax.NO_TAX)
                        .withProduct("music CD", new SalesTax(10))
                        .create();

        ProductStore productStore = new ProductStoreInMemory(new TestDataProductCategories().create());
        return new CashRegister(new TaxCalculator(taxRates), productStore, new ReceiptPrinter());
    }

    @Test
    public void oneItem() {
        CashRegister app = createApp();

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
        CashRegister app = createApp();

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
        CashRegister app = createApp();

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
        CashRegister app = createApp();

        app.purchase(1, "imported book", "10.00");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 imported book: 10.00",
                "Sales Taxes: 0",
                "Total: 10.00"
        }, receipt);
    }

    @Property
    public void oneImportedItemWithNameVariant(String before, String after) {
        assumeThat(before + after, not(""));

        CashRegister app = createApp();

        app.purchase(1, (before + " imported " + after).trim(), "10.00");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 imported " + (before + " " + after).trim() + ": 10.00",
                "Sales Taxes: 0",
                "Total: 10.00"
        }, receipt);
    }

    @Property
    public void oneTaxedItemShouldApplyTaxesForTotal(BigDecimal price) {
        ITaxCalculator foo = (_p, m) -> m.add(BigDecimal.ONE);
        CashRegister app = new CashRegister(foo, new ProductStoreInMemory(new HashMap<>()), new ReceiptPrinter());

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

    @Property
    public void differentQuantitySameTotals(@InRange(min = "1", max = "10000") int quantity) {
        CashRegister app = createApp();
        app.purchase(quantity, "book", "12.49");
        String[] receiptOne = app.getReceipt();

        CashRegister app2 = createApp();
        IntStream.range(0, quantity)
                 .forEach(_i -> app2.purchase(1, "book", "12.49"));

        String[] receiptSum = app2.getReceipt();

        String[] totalOne = Stream.of(receiptOne).filter(s -> s.startsWith("Total:")).toArray(String[]::new);
        String[] totalSum = Stream.of(receiptSum).filter(s -> s.startsWith("Total:")).toArray(String[]::new);

        String[] salesOne = Stream.of(receiptOne).filter(s -> s.startsWith("Sales Taxes:")).toArray(String[]::new);
        String[] salesSum = Stream.of(receiptSum).filter(s -> s.startsWith("Sales Taxes:")).toArray(String[]::new);

        assertArrayEquals(salesOne, salesSum);
        assertArrayEquals(totalOne, totalSum);
    }


}
