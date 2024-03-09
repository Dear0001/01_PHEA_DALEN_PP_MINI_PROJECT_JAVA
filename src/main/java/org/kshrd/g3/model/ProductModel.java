package org.kshrd.g3.model;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    List<Product> unSavedInsertedProducts = new ArrayList<>();
    List<Product> unSavedUpdatedProducts = new ArrayList<>();

    public void addProduct(Product product) {
        unSavedInsertedProducts.add(product);
    }
    public List<Product> getUnSavedInsertedProducts() {
        return unSavedInsertedProducts;
    }
    public void addUnSavedUpdatedProduct(Product product) {
        unSavedUpdatedProducts.add(product);
    }
    public List<Product> getUnSavedUpdatedProducts() {
        return unSavedUpdatedProducts;
    }
}
