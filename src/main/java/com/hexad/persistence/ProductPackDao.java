package com.hexad.persistence;

import com.hexad.model.Product;
import com.hexad.model.ProductPack;
import java.util.List;

/**
 *
 * @author Leonardo Rodrigues
 */
public interface ProductPackDao extends BaseDao<ProductPack, Long>{
    List<ProductPack> findProductPacksByProduct(Product product);
    ProductPack findProductPackByProductAndAmmount(Product product, int ammount);
}
