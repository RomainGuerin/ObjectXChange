package org.esiee.dao;

public interface GenericDao<T> {
    void save(T entity);
    boolean update(T entity);
}
