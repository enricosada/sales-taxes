package io.sada.lmsalestaxes;

import java.math.BigDecimal;

public class MyApp {
    private OrderItem item;

    public void purchase(int quantity, String product, String unitaryPrice) {
        this.purchase(quantity, product, new BigDecimal(unitaryPrice));
    }

    public void purchase(int quantity, String product, BigDecimal unitaryPrice) {
        this.item = new OrderItem(quantity, product, unitaryPrice);
    }

    public String[] getReceipt() {
        return new String[]{
                "1 book: " + this.item.getUnitaryPrice().toString(),
                "Sales Taxes: 0",
                "Total: " + this.item.getUnitaryPrice().toString()
        };
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
