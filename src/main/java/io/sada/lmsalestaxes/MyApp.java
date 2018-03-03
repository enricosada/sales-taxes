package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public class MyApp {
    private final ITaxCalculator taxCalculator;

    private OrderItem item;

    public MyApp(ITaxCalculator taxCalculator){
        this.taxCalculator = taxCalculator;
    }

    public void purchase(int quantity, String product, String unitaryPrice) {
        this.purchase(quantity, product, new BigDecimal(unitaryPrice));
    }

    public void purchase(int quantity, String product, BigDecimal unitaryPrice) {
        this.item = new OrderItem(quantity, product, unitaryPrice);
    }

    public String[] getReceipt() {
        BigDecimal unitaryPrice = this.item.getUnitaryPrice();
        String product = this.item.getProduct();
        BigDecimal itemPrice = getItemPrice(product, unitaryPrice);
        BigDecimal totalPrice = itemPrice;
        BigDecimal salesTaxes = taxCalculator.getSalesTaxes(product, unitaryPrice);
        return new String[]{
                "1 " + product + ": " + itemPrice.toString(),
                "Sales Taxes: " + salesTaxes.toString(),
                "Total: " + totalPrice.toString()
        };
    }

    private BigDecimal getItemPrice(String product, BigDecimal unitaryPrice) {
        if ("book".equals(product)) {
            return unitaryPrice;
        } else {
            return new BigDecimal("16.49");
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
