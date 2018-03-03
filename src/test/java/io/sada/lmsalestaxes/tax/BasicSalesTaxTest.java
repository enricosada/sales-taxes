package io.sada.lmsalestaxes.tax;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.sada.lmsalestaxes.OrderItem;
import io.sada.lmsalestaxes.ProductCategory;
import io.sada.lmsalestaxes.tax.BasicSalesTax;
import io.sada.lmsalestaxes.tax.SalesTax;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class BasicSalesTaxTest {

    @Test
    public void test(){
        SalesTax tax = new SalesTax(100);
        BasicSalesTax basicTax = new BasicSalesTax(tax);

        OrderItem orderItem = new OrderItem(1,"book", BigDecimal.ONE, false, ProductCategory.GENERIC);

        assertEquals(tax, basicTax.getForProduct(orderItem));
    }

    @Property
    public void testWithExemption(ProductCategory category){
        BasicSalesTax basicTax = new BasicSalesTax(new SalesTax(100), category);

        OrderItem orderItem = new OrderItem(1,"book", BigDecimal.ONE, false, category);

        assertEquals(SalesTax.NO_TAX, basicTax.getForProduct(orderItem));
    }

    @Property
    public void testWithExemptions(ProductCategory[] categories){
        BasicSalesTax basicTax = new BasicSalesTax(new SalesTax(100), categories);

        for (ProductCategory category: categories) {
            OrderItem orderItem = new OrderItem(1, "book", BigDecimal.ONE, false, category);

            assertEquals(SalesTax.NO_TAX, basicTax.getForProduct(orderItem));
        }
    }
}
