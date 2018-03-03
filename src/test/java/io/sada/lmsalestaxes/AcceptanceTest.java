package io.sada.lmsalestaxes;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AcceptanceTest {

    private MyApp createApp() {
        ITaxRatesForProduct basicTaxes = new BasicSalesTax(BasicSalesTax.BASIC_TAX, BasicSalesTax.EXEMPTED_CATEGORIES);
        ITaxRatesForProduct importDuty = new ImportDutySalesTax(ImportDutySalesTax.IMPORT_DUTY);

        ProductStore productStore = new ProductStoreInMemory(new TestDataProductCategories().create());
        return new MyApp(new TaxCalculator(basicTaxes, importDuty), productStore);
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

    @Test
    public void scenario2() {
        MyApp app = createApp();

        app.purchase(1, "imported box of chocolates", "10.00");
        app.purchase(1, "imported bottle of perfume", "47.50");

        String[] receipt = app.getReceipt();

        assertArrayEquals(new String[]{
                "1 imported box of chocolates: 10.50",
                "1 imported bottle of perfume: 54.65",
                "Sales Taxes: 7.65",
                "Total: 65.15"
        }, receipt);
    }

    @Test
    public void scenario3() {
        MyApp app = createApp();

        app.purchase(1, "imported bottle of perfume", "27.99");
        app.purchase(1, "bottle of perfume", "18.99");
        app.purchase(1, "packet of headache pills", "9.75");
        app.purchase(1, "box of imported chocolates", "11.25");

        String[] receipt = app.getReceipt();

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
