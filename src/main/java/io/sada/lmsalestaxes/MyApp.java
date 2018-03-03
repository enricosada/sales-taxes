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
        this.purchase(new OrderItem(quantity, product, unitaryPrice));
    }

    public void purchase(OrderItem... items) {
        for (OrderItem i : items) {
            this.items.add(i);
        }
    }

    public String[] getReceipt() {
        List<OrderItemPurchased> orderLines =
            this.items
                .stream()
                .map(item -> {
                    BigDecimal unitaryPrice = item.getUnitaryPrice();
                    String product = item.getProduct();
                    BigDecimal salesTaxes = taxCalculator.getSalesTaxes(product, unitaryPrice);
                    BigDecimal itemPrice = unitaryPrice.add(salesTaxes);
                    return new OrderItemPurchased(item, itemPrice, salesTaxes);
                })
                .collect(Collectors.toList());

        BigDecimal totalPrice = orderLines.stream().map(item -> item.getItemPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSalesTaxes = orderLines.stream().map(item -> item.getSalesTaxes()).reduce(BigDecimal.ZERO, BigDecimal::add);

        List<String> itemLines =
                orderLines
                    .stream()
                    .map(item -> "1 " + item.getItem().getProduct() + ": " + item.getItemPrice().toString())
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

    private final class OrderItem {
        private final int quantity;
        private final String product;
        private final BigDecimal unitaryPrice;

        public OrderItem(int quantity, String product, BigDecimal unitaryPrice) {
            this.quantity = quantity;
            this.product = product;
            this.unitaryPrice = unitaryPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getProduct() {
            return product;
        }

        public BigDecimal getUnitaryPrice() {
            return unitaryPrice;
        }
    }
}
