package com.hexad.persistence;

import com.hexad.model.Product;
import com.hexad.model.ProductPack;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Leonardo Rodrigues
 */
public class HibernateProductPackDao extends HibernateDao<ProductPack, Long> implements ProductPackDao {
    public HibernateProductPackDao() {
        this.entityName = "ProductPack";
        this.entityClass = ProductPack.class;
    }

    @Override
    public List<ProductPack> findProductPacksByProduct(Product product) {
        Session session = sessionFactory.openSession();
        return session.createQuery("from ProductPack where product = :product")
                      .setParameter("product", product)
                      .list();
    }

    @Override
    public ProductPack findProductPackByProductAndAmmount(Product product, int ammount) {
        Session session = sessionFactory.openSession();
        return (ProductPack)session.createQuery("from ProductPack where product = :product and ammount = :ammount")
                      .setParameter("product", product)
                      .setParameter("ammount", ammount)
                      .getSingleResult();
    }
    
    
}
