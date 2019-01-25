package com.hexad.persistence;

import com.hexad.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Rodrigues
 */
public class MockProductDao implements ProductDao {
    protected static final Product VEGEMITE_PRODUCT = new Product("VS5", "Vegemite Scroll");
    protected static final Product MUFFIN_PRODUCT = new Product("MB11", "Blueberry Muffin");
    protected static final Product CROISSANT_PRODUCT = new Product ("CF", "Croissant");
    
    private static final List<Product> PRODUCTS = new ArrayList<>();
    
    public MockProductDao() {
        PRODUCTS.add(VEGEMITE_PRODUCT);
        PRODUCTS.add(MUFFIN_PRODUCT);
        PRODUCTS.add(CROISSANT_PRODUCT);
    }

    @Override
    public List<Product> list() {
        return PRODUCTS;
    }

    @Override
    public Product find(String code) {
        return PRODUCTS.stream()
                       .filter(p -> p.getCode().equals(code))
                       .findFirst()
                       .orElse(null);
    }

    @Override
    public void save(Product entity) {
        throw new UnsupportedOperationException("Mock cannot be saved!"); //To change body of generated methods, choose Tools | Templates.
    }
}
