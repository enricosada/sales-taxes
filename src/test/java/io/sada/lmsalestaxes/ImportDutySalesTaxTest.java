package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Ctor;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class ImportDutySalesTaxTest {

    @Property
    public void applyIfIsImported(@From(Ctor.class) OrderItem orderItem){
        assumeThat(orderItem.getIsImported(), is(true));

        SalesTax tax = new SalesTax(100);
        ImportDutySalesTax importDuty = new ImportDutySalesTax(tax);

        assertEquals(tax, importDuty.getForProduct(orderItem));
    }

    @Property
    public void exemptIfNotImported(@From(Ctor.class) OrderItem orderItem){
        assumeThat(orderItem.getIsImported(), is(false));

        SalesTax tax = new SalesTax(1000);
        ImportDutySalesTax importDuty = new ImportDutySalesTax(tax);

        assertEquals(SalesTax.NO_TAX, importDuty.getForProduct(orderItem));
    }
}