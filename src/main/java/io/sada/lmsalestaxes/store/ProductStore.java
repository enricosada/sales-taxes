package io.sada.lmsalestaxes.store;

import io.sada.lmsalestaxes.store.ProductCategory;

public interface ProductStore {
    ProductCategory getCategoryOf(String productName);
}

