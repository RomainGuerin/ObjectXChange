package org.esiee.manager;

import org.esiee.services.UserService;
import org.esiee.model.User;

public class UserManager {
    private final UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    public void register(String name, String email, String password) {
        userService.registerUser(new User(name, email, password));
    }

    public User login(String email, String password) {
        return userService.loginUser(new User(email, password));
    }
}