package io.sada.lmsalestaxes;

import java.util.HashMap;

public class TestDataProductCategories {
    public HashMap create() {
        HashMap cat = new HashMap();
        cat.put("book", ProductCategory.BOOK);
        cat.put("music CD", ProductCategory.GENERIC);
        cat.put("chocolate bar", ProductCategory.FOOD);
        return cat;
    }
}
