package org.esiee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:database.db";
    private static Connection connection;

    private DatabaseConnection() {}

    /**
     * Retrieves a connection to the database. If the connection is closed or null, a new connection is created.
     *
     * @return a Connection object to the database
     * @throws RuntimeException if the JDBC driver is not found or a database access error occurs
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur: Driver JDBC SQLite introuvable", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        }
        return connection;
    }
}
