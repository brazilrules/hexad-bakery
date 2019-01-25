package com.hexad.persistence;

import com.hexad.model.Product;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Leonardo Rodrigues
 */
public class HibernateProductDao extends HibernateDao<Product, String> implements ProductDao {

    SessionFactory sessionFactory;
    
    public HibernateProductDao() {
        this.entityName = "Product";
        this.entityClass = Product.class;
    }
    
}
