package com.hexad.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Leonardo Rodrigues
 */
public class HibernateDao<T, K extends Serializable> implements BaseDao<T, K>{
    protected SessionFactory sessionFactory;
    protected String entityName;
    protected Class entityClass;
    
    public HibernateDao() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build()); 
    }
    
    @Override
    public List<T> list() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from " + entityName).list();
    }

    @Override
    public T find(K id) {
        Session session = sessionFactory.openSession();
        return (T)session.get(entityClass, id);
    }

    @Override
    public void save(T entity) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(entity);
    }
    
}
