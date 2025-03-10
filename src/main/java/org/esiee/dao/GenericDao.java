package org.esiee.dao;

/**
 * GenericDao interface provides generic methods to perform CRUD operations on entities.
 *
 * @param <T> the type of the entity
 */
public interface GenericDao<T> {

    /**
     * Saves a new entity to the database.
     *
     * @param entity the entity to be saved
     */
    void save(T entity);

    /**
     * Updates an existing entity in the database.
     *
     * @param entity the entity to be updated
     * @return true if the update was successful, false otherwise
     */
    boolean update(T entity);
}