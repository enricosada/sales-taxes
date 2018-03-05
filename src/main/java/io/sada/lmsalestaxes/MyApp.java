package io.sada.lmsalestaxes;

import io.sada.lmsalestaxes.tax.ITaxCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyApp {
    private final ITaxCalculator taxCalculator;
    private final ProductStore productStore;
    private final ReceiptPrinter receipt;

    private List<OrderItem> items;

    public MyApp(ITaxCalculator taxCalculator, ProductStore productStore, ReceiptPrinter receipt){
        this.taxCalculator = taxCalculator;
        this.productStore = productStore;
        this.receipt = receipt;
        this.items = new ArrayList<>();
    }

    public void purchase(int quantity, String displayName, String unitaryPrice) {
        this.purchase(quantity, displayName, new BigDecimal(unitaryPrice));
    }

    public void purchase(int quantity, String displayName, BigDecimal unitaryPrice) {
        OrderItem item = findItem(quantity, displayName, unitaryPrice);

        this.items.add(item);
    }

    private OrderItem findItem(int quantity, String displayName, BigDecimal unitaryPrice) {
        boolean isImported = (" " + displayName + " ").contains(" imported ");
        String productName;
        if (isImported)
        {
            productName = displayName
                            .replace(" imported ", " ")
                            .replace("imported ", "")
                            .trim();
        }
        else
            productName = displayName.trim();

        ProductCategory category = productStore.getCategoryOf(productName);

        return new OrderItem(quantity, productName, unitaryPrice, isImported, category);
    }

    public String[] getReceipt() {
        List<ReceiptItem> orderLines =
            this.items
                .stream()
                .map(this::toReceiptItem)
                .collect(Collectors.toList());

        BigDecimal totalPrice = orderLines.stream().map(item -> item.getItemPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSalesTaxes = orderLines.stream().map(item -> item.getSalesTaxes()).reduce(BigDecimal.ZERO, BigDecimal::add);
        String[] strings = receipt.generate(orderLines, totalPrice, totalSalesTaxes);
        return strings;
    }

    private ReceiptItem toReceiptItem(OrderItem item) {
        BigDecimal price = item.getUnitaryPrice().multiply(new BigDecimal(item.getQuantity()));
        BigDecimal salesTaxes = taxCalculator.getSalesTaxes(item, price);
        BigDecimal itemPrice = price.add(salesTaxes);
        String displayName = item.getIsImported()? "imported " + item.getProduct() : item.getProduct();
        return new ReceiptItem(displayName, itemPrice, salesTaxes);
    }
}


