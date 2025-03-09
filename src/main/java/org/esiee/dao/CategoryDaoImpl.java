package org.esiee.dao;

import org.esiee.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void save(Category entity) {
        // It's a DAO
    }

    @Override
    public boolean update(Category entity) {
        return false;
    }

    @Override
    public List<Category> getAllCategory() {
        String query = "SELECT id, name FROM Category";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            List<Category> categories = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
                return categories;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all categories: " + e.getMessage(), e);
        }
    }

}
