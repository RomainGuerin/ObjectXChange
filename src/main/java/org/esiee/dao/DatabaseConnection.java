package org.esiee.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:database.db";
    private static Connection connection;
    private DatabaseConnection() {};

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }
}
