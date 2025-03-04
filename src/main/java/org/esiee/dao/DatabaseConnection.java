package org.esiee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "dbc:sqlite:database.db";
    private static Connection connection;

    private DatabaseConnection() {};

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
