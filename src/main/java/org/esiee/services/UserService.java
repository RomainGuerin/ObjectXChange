package org.esiee.services;

import org.esiee.dao.UserDao;
import org.esiee.model.User;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(User user) {
        if (userDao.getByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        userDao.save(user);
    }

    public User loginUser(User user) {
        User validUser = userDao.getByEmail(user.getEmail());
        if (validUser == null || !validUser.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return validUser;
    }
}