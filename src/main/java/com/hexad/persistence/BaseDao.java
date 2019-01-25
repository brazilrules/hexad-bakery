package com.hexad.persistence;

import java.util.List;

/**
 *
 * @author Leonardo Rodrigues
 */
public interface BaseDao<T, K> {
    List<T> list();
    T find(K id);
    void save(T entity);
}
