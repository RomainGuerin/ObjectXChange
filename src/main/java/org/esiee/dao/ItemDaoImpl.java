package org.esiee.dao;

import org.esiee.model.Category;
import org.esiee.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public void save(Item entity) {
        String query = "INSERT INTO Item (name, userId, category) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getUserId());
            ps.setInt(3, entity.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving item: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Item entity) {
        String query = "UPDATE Item SET name = ?, userId = ?, category = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getUserId());
            ps.setInt(3, entity.getCategory());
            ps.setInt(4, entity.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Item> getAllItems() {
        String query = "SELECT * FROM Item";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            return getItems(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all items: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Item> getItemsByUserId(int userId) {
        String query = "SELECT * FROM Item WHERE userId = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            return getItems(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting items by user id: " + e.getMessage(), e);
        }
    }

    private List<Item> getItems(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("userId"),
                        rs.getInt("category")
                ));
            }
            return items;
        }
    }

    @Override
    public List<Item> getFilteredItems(String name, int categoryId) {
        String query = "SELECT * FROM Item WHERE name LIKE ? OR category = ?"; //TODO : AND or OR
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, categoryId);
            return getItems(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting filtered items: " + e.getMessage(), e);
        }
    }
}
