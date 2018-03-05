package io.sada.lmsalestaxes;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.sada.lmsalestaxes.store.ProductCategory;
import io.sada.lmsalestaxes.store.ProductStoreInMemory;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


@RunWith(JUnitQuickcheck.class)
public class ProductStoreInMemoryTest {

    @Property
    public void defaultIsGENERIC(String productName){
        ProductStoreInMemory p = new ProductStoreInMemory(new HashMap<>());
        assertEquals(ProductCategory.GENERIC, p.getCategoryOf(productName));
    }

    @Property
    public void productWithCategory(String productName, ProductCategory category){
        HashMap<String, ProductCategory> map = new HashMap<>();
        map.put(productName, category);
        ProductStoreInMemory p = new ProductStoreInMemory(map);
        assertEquals(category, p.getCategoryOf(productName));
    }

}