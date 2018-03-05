package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.receipt.ReceiptPrinter;
import io.sada.lmsalestaxes.receipt.ReceiptTextFormatter;
import io.sada.lmsalestaxes.store.ProductStore;
import io.sada.lmsalestaxes.store.ProductStoreInMemory;
import io.sada.lmsalestaxes.tax.BasicSalesTax;
import io.sada.lmsalestaxes.tax.ITaxRatesForProduct;
import io.sada.lmsalestaxes.tax.ImportDutySalesTax;
import io.sada.lmsalestaxes.tax.TaxCalculator;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AcceptanceTest {

    private CashRegister createApp(CashRegisterScreen screen) {
        ITaxRatesForProduct basicTaxes = new BasicSalesTax(BasicSalesTax.BASIC_TAX, BasicSalesTax.EXEMPTED_CATEGORIES);
        ITaxRatesForProduct importDuty = new ImportDutySalesTax(ImportDutySalesTax.IMPORT_DUTY);

        ProductStore productStore = new ProductStoreInMemory(new TestDataProductCategories().create());
        return new CashRegister(new TaxCalculator(basicTaxes, importDuty), productStore, new ReceiptPrinter(), screen);
    }

    private CashRegisterScreen createScreen() {
        return new CashRegisterScreenAsLines(new ReceiptTextFormatter());
    }

    @Test
    public void scenario1() {
        CashRegisterScreen screen = createScreen();
        CashRegister app = createApp(screen);

        app.include(1, "book", "12.49");
        app.include(1, "music CD", "14.99");
        app.include(1, "chocolate bar", "0.85");

        app.purchase();
        String[] receipt = screen.getLines();

        assertArrayEquals(new String[]{
                "1 book: 12.49",
                "1 music CD: 16.49",
                "1 chocolate bar: 0.85",
                "Sales Taxes: 1.50",
                "Total: 29.83"
        }, receipt);
    }

    @Test
    public void scenario2() {
        CashRegisterScreen screen = createScreen();
        CashRegister app = createApp(screen);

        app.include(1, "imported box of chocolates", "10.00");
        app.include(1, "imported bottle of perfume", "47.50");

        app.purchase();
        String[] receipt = screen.getLines();

        assertArrayEquals(new String[]{
                "1 imported box of chocolates: 10.50",
                "1 imported bottle of perfume: 54.65",
                "Sales Taxes: 7.65",
                "Total: 65.15"
        }, receipt);
    }

    @Test
    public void scenario3() {
        CashRegisterScreen screen = createScreen();
        CashRegister app = createApp(screen);

        app.include(1, "imported bottle of perfume", "27.99");
        app.include(1, "bottle of perfume", "18.99");
        app.include(1, "packet of headache pills", "9.75");
        app.include(1, "box of imported chocolates", "11.25");

        app.purchase();
        String[] receipt = screen.getLines();

        assertArrayEquals(new String[]{
                "1 imported bottle of perfume: 32.19",
                "1 bottle of perfume: 20.89",
                "1 packet of headache pills: 9.75",
                "1 imported box of chocolates: 11.85",
                "Sales Taxes: 6.70",
                "Total: 74.68"
        }, receipt);
    }
}
