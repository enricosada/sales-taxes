package io.sada.lmsalestaxes;

public class ImportDutySalesTax implements ITaxRatesForProduct {
    public final static SalesTax IMPORT_DUTY = new SalesTax(5);

    private final SalesTax tax;

    public ImportDutySalesTax(SalesTax tax) {
        this.tax = tax;
    }

    public SalesTax getForProduct(OrderItem product) {
        return product.getIsImported()? tax : SalesTax.NO_TAX;
    }
}
