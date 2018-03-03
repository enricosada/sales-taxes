package io.sada.lmsalestaxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyApp {
    private final ITaxCalculator taxCalculator;

    private List<OrderItem> items;

    public MyApp(ITaxCalculator taxCalculator){
        this.taxCalculator = taxCalculator;
        this.items = new ArrayList<>();
    }

    public void purchase(int quantity, String product, String unitaryPrice) {
        this.purchase(quantity, product, new BigDecimal(unitaryPrice));
    }

    public void purchase(int quantity, String product, BigDecimal unitaryPrice) {
        boolean isImported = product.startsWith("imported ");
        String productName = isImported? product.replace("imported ", "") : product;

        OrderItem item = new OrderItem(quantity, productName, unitaryPrice, isImported);
        this.items.add(item);
    }

    public String[] getReceipt() {
        List<OrderItemPurchased> orderLines =
            this.items
                .stream()
                .map(this::getOrderItemPurchased)
                .collect(Collectors.toList());

        BigDecimal totalPrice = orderLines.stream().map(item -> item.getItemPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSalesTaxes = orderLines.stream().map(item -> item.getSalesTaxes()).reduce(BigDecimal.ZERO, BigDecimal::add);
        String[] strings = generateReceiptLines(orderLines, totalPrice, totalSalesTaxes);
        return strings;
    }

    private OrderItemPurchased getOrderItemPurchased(OrderItem item) {
        BigDecimal unitaryPrice = item.getUnitaryPrice();
        String product = item.getProduct();
        BigDecimal salesTaxes = taxCalculator.getSalesTaxes(item, unitaryPrice);
        BigDecimal itemPrice = unitaryPrice.add(salesTaxes);
        return new OrderItemPurchased(item, itemPrice, salesTaxes);
    }

    private String[] generateReceiptLines(List<OrderItemPurchased> orderLines, BigDecimal totalPrice, BigDecimal totalSalesTaxes) {

        List<String> itemLines =
                orderLines
                    .stream()
                    .map(item -> {
                        OrderItem x = item.getItem();
                        String displayName = x.getIsImported()? "imported " + x.getProduct() : x.getProduct();
                        return "1 " + displayName + ": " + item.getItemPrice().toString();
                    })
                    .collect(Collectors.toList());

        String[] totals = {
                "Sales Taxes: " + totalSalesTaxes.toString(),
                "Total: " + totalPrice.toString() };

        return Stream.concat(itemLines.stream(), Stream.of(totals)).toArray(String[]::new);
    }

    private final class OrderItemPurchased {
        private final OrderItem item;
        private final BigDecimal itemPrice;
        private final BigDecimal salesTaxes;

        public OrderItemPurchased(OrderItem item, BigDecimal itemPrice, BigDecimal salesTaxes) {

            this.item = item;
            this.itemPrice = itemPrice;
            this.salesTaxes = salesTaxes;
        }

        public OrderItem getItem() {
            return item;
        }

        public BigDecimal getItemPrice() {
            return itemPrice;
        }

        public BigDecimal getSalesTaxes() {
            return salesTaxes;
        }
    }
}

