package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class SalesTaxTest {

    @Property
    public void rateZero(BigDecimal amount) {
        SalesTax tax = new SalesTax(0);
        assertEquals(BigDecimal.ZERO, tax.getAmountFor(amount));
    }

    @Test
    public void sample() {
        SalesTax tax = new SalesTax(10);
        BigDecimal result = tax.getAmountFor(new BigDecimal("14.99"));
        assertEquals(new BigDecimal("1.50"), result);
    }

    @Test
    public void rounding() {
        SalesTax tax = new SalesTax(100);
        BigDecimal result = tax.getAmountFor(new BigDecimal("0.04"));
        assertEquals(new BigDecimal("0.05"), result);
    }
}
