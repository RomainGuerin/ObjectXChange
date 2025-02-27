package org.esiee;

import org.esiee.dao.UserDao;
import org.esiee.dao.UserDaoImpl;
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
        // URL of the SQLite database
        String url = "jdbc:sqlite:database.db";

        // Try-with-resources statement to ensure the connection is closed automatically
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connexion à SQLite établie.");

                // Initialize UserDao implementation
                UserDao userDao = new UserDaoImpl();

                // Initialize UserService with the UserDao
                UserService userService = new UserService(userDao);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}