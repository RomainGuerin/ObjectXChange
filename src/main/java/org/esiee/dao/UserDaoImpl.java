package org.esiee.dao;

import org.esiee.model.User;

import java.sql.*;

/**
 * Implementation of the UserDao interface for interacting with the User data in a SQLite database.
 */
public class UserDaoImpl implements UserDao {
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

    @Override
    public User getById(int id) { return null; }

    @Override
    public User getByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = ?";
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

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }
}