package io.sada.lmsalestaxes.tax;

import io.sada.lmsalestaxes.OrderItem;
import io.sada.lmsalestaxes.ProductCategory;

import java.util.stream.Stream;

public class BasicSalesTax implements ITaxRatesForProduct {
    public static final SalesTax BASIC_TAX = new SalesTax(10);
    public static final ProductCategory[] EXEMPTED_CATEGORIES = new ProductCategory[] {
            ProductCategory.BOOK,
            ProductCategory.FOOD,
            ProductCategory.MEDICAL
    };

    private final SalesTax defaultTax;
    private final ProductCategory[] exemptions;

    public BasicSalesTax(SalesTax defaultTax, ProductCategory... exemptions) {
        this.defaultTax = defaultTax;
        this.exemptions = exemptions;
    }

    public SalesTax getForProduct(OrderItem product) {
        ProductCategory cat = product.getCategory();
        if (Stream.of(exemptions).filter(exception -> exception.equals(cat)).findAny().isPresent()) {
            return SalesTax.NO_TAX;
        }

        return defaultTax;
    }
}
