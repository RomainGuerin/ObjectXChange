package org.esiee.dao;

import org.esiee.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void save(Product entity) {
        String query = "INSERT INTO Product (name, description, images, user_id, category_id, availability) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getImage());
            ps.setInt(4, entity.getUserId());
            ps.setInt(5, entity.getCategoryId());
            ps.setBoolean(6, entity.isAvailable());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving product: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Product entity) {
        String query = "UPDATE Product SET availability = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setBoolean(1, entity.isAvailable());
            ps.setInt(2, entity.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        String query = "SELECT id, name, description, date_created, images, user_id, category_id, availability FROM Product ORDER BY date_created DESC";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            return getProduct(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getProductByUserId(int userId) {
        String query = "SELECT id, name, description, date_created, images, user_id, category_id, availability FROM Product WHERE user_id = ? ORDER BY date_created DESC";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            return getProduct(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting products by user id: " + e.getMessage(), e);
        }
    }

    private List<Product> getProduct(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("date_created"),
                        rs.getString("images"),
                        rs.getInt("user_id"),
                        rs.getInt("category_id"),
                        rs.getBoolean("availability")
                ));
            }
            return products;
        }
    }

    @Override
    public List<Product> getFilteredProducts(String name, int categoryId) {
        String query = "SELECT id, name, description, date_created, images, user_id, category_id, availability FROM Product WHERE name LIKE ?";

        if (categoryId > 0) {
            query += " AND category_id = ?";
        }

        query += " ORDER BY date_created DESC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%");

            if (categoryId > 0) {
                ps.setInt(2, categoryId);
            }

            return getProduct(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting filtered products: " + e.getMessage(), e);
        }
    }

    @Override
    public Product getProductById(int productId) {
        String query = "SELECT id, name, description, date_created, images, user_id, category_id, availability FROM Product WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDate("date_created"),
                            rs.getString("images"),
                            rs.getInt("user_id"),
                            rs.getInt("category_id"),
                            rs.getBoolean("availability")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting product by id: " + e.getMessage(), e);
        }
        return null;
    }
}