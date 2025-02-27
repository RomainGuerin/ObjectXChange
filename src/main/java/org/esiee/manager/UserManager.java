package org.esiee.manager;

import org.esiee.services.UserService;
import org.esiee.model.User;

/**
 * Manager class for handling user registration and login operations.
 */
public class UserManager {
    private UserService userService;

    /**
     * Constructs a UserManager with the specified UserService.
     *
     * @param userService The UserService to be used by this manager
     */
    public UserManager(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user with the specified name, password, and email.
     *
     * @param name The name of the user
     * @param password The password of the user
     * @param email The email of the user
     */
    public void register(String name, String password, String email) {
        userService.registerUser(name, password, email);
    }

    /**
     * Logs in a user with the specified name and password.
     *
     * @param name The name of the user
     * @param password The password of the user
     * @return The logged-in user
     */
    public User login(String name, String password) {
        return userService.loginUser(name, password);
    }
}