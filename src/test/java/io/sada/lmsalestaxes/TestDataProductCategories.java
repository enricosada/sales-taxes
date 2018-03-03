package io.sada.lmsalestaxes;

import java.util.HashMap;

public class TestDataProductCategories {
    public HashMap create() {
        HashMap cat = new HashMap();
        cat.put("book", ProductCategory.BOOK);
        cat.put("music CD", ProductCategory.GENERIC);
        cat.put("chocolate bar", ProductCategory.FOOD);
        cat.put("box of chocolates", ProductCategory.FOOD);
        cat.put("bottle of perfume", ProductCategory.GENERIC);
        cat.put("packet of headache pills", ProductCategory.MEDICAL);
        return cat;
    }
}
