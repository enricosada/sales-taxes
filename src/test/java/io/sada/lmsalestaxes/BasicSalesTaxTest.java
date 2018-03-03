package io.sada.lmsalestaxes;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BasicSalesTaxTest {

    @Test
    public void test(){
        SalesTax tax = new SalesTax(100);
        BasicSalesTax basicTax = new BasicSalesTax(tax);

        OrderItem orderItem = new OrderItem(1,"book", BigDecimal.ONE, false, ProductCategory.GENERIC);

        assertEquals(tax, basicTax.getForProduct(orderItem));
    }
}
