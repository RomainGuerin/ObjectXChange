package org.esiee.dao;

import org.esiee.model.User;

/**
 * UserDao interface provides methods to perform CRUD operations on User objects.
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Retrieves a User entity by its email.
     *
     * @param email the email of the user
     * @return the User object with the specified email
     */
    User getByEmail(String email);
}