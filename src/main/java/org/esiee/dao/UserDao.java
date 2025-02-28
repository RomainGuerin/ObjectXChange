package org.esiee.dao;

import org.esiee.model.User;

/**
 * UserDao is an interface that provides methods to interact with the User data.
 */
public interface UserDao {
    /**
     * Saves a user to the database.
     *
     * @param user The user to be saved
     */
    void save(User user);

    /**
     * Finds a user by their name.
     *
     * @param name The name of the user to find
     * @return The user with the specified name, or null if not found
     */
    User findByName(String name);

    /**
     * Finds a user by their email.
     *
     * @param email The email of the user to find
     * @return The user with the specified email, or null if not found
     */
    User findByEmail(String email);
}