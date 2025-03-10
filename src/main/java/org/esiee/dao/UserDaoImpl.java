package org.esiee.dao;

import org.esiee.model.User;

import java.sql.*;

/**
 * Implementation of the UserDao interface for interacting with the User data in a SQLite database.
 */
public class UserDaoImpl implements UserDao {
    /**
     * Saves a new User entity to the database.
     *
     * @param entity the User entity to be saved
     */
    @Override
    public void save(User entity) {
        String query = "INSERT INTO User (email, name, password) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing User entity in the database.
     *
     * @param entity the User entity to be updated
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean update(User entity) {
        return false;
    }

    /**
     * Retrieves a User entity by its email.
     *
     * @param email the email of the user
     * @return the User object with the specified email, or null if not found
     */
    @Override
    public User getByEmail(String email) {
        String query = "SELECT id, name, email, password FROM User WHERE email = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user by email: " + e.getMessage(), e);
        }
        return null;
    }
}