package com.hexad.persistence;

import com.hexad.model.Product;
import com.hexad.model.ProductPack;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Leonardo Rodrigues
 */
public class MockProductPackDao implements ProductPackDao {
    private static final ProductPack VEGEMITE_3 = new ProductPack(1L, MockProductDao.VEGEMITE_PRODUCT, 3, 6.99);
    private static final ProductPack VEGEMITE_5 = new ProductPack(2L, MockProductDao.VEGEMITE_PRODUCT, 5, 8.99);
    
    private static final ProductPack MUFFIN_2 = new ProductPack(3L, MockProductDao.MUFFIN_PRODUCT, 2, 9.95);
    private static final ProductPack MUFFIN_5 = new ProductPack(4L, MockProductDao.MUFFIN_PRODUCT, 5, 16.95);
    private static final ProductPack MUFFIN_8 = new ProductPack(5L, MockProductDao.MUFFIN_PRODUCT, 8, 24.95);
    
    private static final ProductPack CROISSANT_3 = new ProductPack(6L, MockProductDao.CROISSANT_PRODUCT, 3, 5.95);
    private static final ProductPack CROISSANT_5 = new ProductPack(7L, MockProductDao.CROISSANT_PRODUCT, 5, 9.95);
    private static final ProductPack CROISSANT_9 = new ProductPack(8L, MockProductDao.CROISSANT_PRODUCT, 9, 16.99);
    
    private static final List<ProductPack> PRODUCT_PACKS = new ArrayList<>();
    
    public MockProductPackDao() {
        PRODUCT_PACKS.add(VEGEMITE_3);
        PRODUCT_PACKS.add(VEGEMITE_5);
        
        PRODUCT_PACKS.add(MUFFIN_2);
        PRODUCT_PACKS.add(MUFFIN_5);
        PRODUCT_PACKS.add(MUFFIN_8);
        
        PRODUCT_PACKS.add(CROISSANT_3);
        PRODUCT_PACKS.add(CROISSANT_5);
        PRODUCT_PACKS.add(CROISSANT_9);
    }

    @Override
    public List<ProductPack> list() {
        return PRODUCT_PACKS;
    }

    @Override
    public void save(ProductPack entity) {
        throw new UnsupportedOperationException("Not possible to save on a mock!");
    }
    
    @Override
    public ProductPack find(Long id) {
        return PRODUCT_PACKS.stream()
                            .filter(p -> p.getId().equals(id))
                            .findFirst()
                            .orElse(null);
    }

    @Override
    public List<ProductPack> findProductPacksByProduct(Product product) {
        return PRODUCT_PACKS.stream()
                            .filter(p -> p.getProduct().equals(product))
                            .collect(Collectors.toList());
    }

    @Override
    public ProductPack findProductPackByProductAndAmmount(Product product, int ammount) {
        return PRODUCT_PACKS.stream()
                            .filter(p -> p.getProduct().equals(product) && p.getAmmount() == ammount)
                            .findFirst()
                            .orElse(null);
    }
}
