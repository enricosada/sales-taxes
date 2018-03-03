package io.sada.lmsalestaxes;

import java.util.Map;

public class ProductStoreInMemory implements ProductStore {
    private Map<String, ProductCategory> categories;

    public ProductStoreInMemory(Map<String, ProductCategory> categories) {
        this.categories = categories;
    }

    public ProductCategory getCategoryOf(String productName) {
        return categories.getOrDefault(productName, ProductCategory.GENERIC);
    }
}
