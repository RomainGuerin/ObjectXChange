package org.esiee;

import org.esiee.dao.UserDao;
import org.esiee.dao.UserDaoImpl;
import org.esiee.manager.UserManager;
import org.esiee.model.User;
import org.esiee.services.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The main application class that establishes a connection to a SQLite database
 * and initializes the UserDao and UserService.
 */
public class App {
    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Initialize UserDao implementation
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserService(userDao);
        UserManager userManager = new UserManager(userService);

        // Register a new user
//        userManager.register("John Doe", "test", "password");

        // Login a user
//        User test = userManager.login("test", "password");
//        System.out.println(test.getId());
    }
}