package io.sada.lmsalestaxes.receipt;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Ctor;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.hamcrest.core.Is;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class ReceiptTextFormatterTest {

    @Property
    public void total(BigDecimal total) {
        ReceiptTextFormatter formatter = new ReceiptTextFormatter();

        Receipt receipt = new Receipt(new ArrayList<>(), total, BigDecimal.ZERO);

        String[] result = formatter.print(receipt);
        String totals[] = Stream.of(result).filter(line -> line.startsWith("Total: ")).toArray(String[]::new);

        assertArrayEquals(new String[] { "Total: " + receipt.getTotalPrice().toString()}, totals);
    }

    @Property
    public void salesTotal(BigDecimal total) {
        ReceiptTextFormatter formatter = new ReceiptTextFormatter();

        Receipt receipt = new Receipt(new ArrayList<>(), BigDecimal.ZERO, total);

        String[] result = formatter.print(receipt);
        String totals[] = Stream.of(result).filter(line -> line.startsWith("Sales Taxes: ")).toArray(String[]::new);

        assertArrayEquals(new String[] { "Sales Taxes: " + receipt.getTotalSalesTaxes().toString()}, totals);
    }

    @Property
    public void item(int quantity, String name, BigDecimal price, BigDecimal salesTax) {
        ReceiptItem item = new ReceiptItem(quantity, name, price, salesTax);
        ReceiptTextFormatter formatter = new ReceiptTextFormatter();
        Receipt receipt = new Receipt(Stream.of(new ReceiptItem[] {item}).collect(Collectors.toList()), BigDecimal.ZERO, BigDecimal.ZERO);

        String[] result1 = formatter.print(receipt);

        String[] expected = new String[] {
                quantity + " " + name + ": " + price,
                "Sales Taxes: 0",
                "Total: 0",
        };

        assertArrayEquals(expected, result1);
    }

}
