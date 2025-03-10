package org.esiee.dao;

    import org.esiee.model.Product;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    /**
     * Implementation of the ProductDao interface for performing CRUD operations on Product objects.
     */
    public class ProductDaoImpl implements ProductDao {

        /**
         * Saves a new Product entity to the database.
         *
         * @param entity the Product entity to be saved
         */
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

        /**
         * Updates an existing Product entity in the database.
         *
         * @param entity the Product entity to be updated
         * @return true if the update was successful, false otherwise
         */
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

        /**
         * Retrieves a list of all products from the database.
         *
         * @return a list of Product objects
         */
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

    /**
     * Retrieves a list of products by a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of Product objects associated with the user ID
     */
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

        /**
         * Helper method to execute a query and retrieve a list of Product objects.
         *
         * @param ps the PreparedStatement to be executed
         * @return a list of Product objects
         * @throws SQLException if a database access error occurs
         */
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

    /**
     * Retrieves a list of products filtered by name and category ID.
     *
     * @param name the name of the product
     * @param categoryId the ID of the category
     * @return a list of filtered Product objects
     */
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

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product
     * @return the Product object with the specified ID
     */
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