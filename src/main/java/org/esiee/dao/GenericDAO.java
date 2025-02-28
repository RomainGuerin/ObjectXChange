package org.esiee.dao;

public interface GenericDAO<T> {
    boolean save(T entity);
    T getById(int id);
    boolean update(T entity);
    boolean delete(T entity);
}
