package org.esiee.dao;

    import org.esiee.model.Category;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    
    /**
     * Implementation of the CategoryDao interface for performing CRUD operations on Category objects.
     */
    public class CategoryDaoImpl implements CategoryDao {

        /**
         * Saves a new Category entity to the database.
         *
         * @param entity the Category entity to be saved
         */
        @Override
        public void save(Category entity) {}

        /**
         * Updates an existing Category entity in the database.
         *
         * @param entity the Category entity to be updated
         * @return true if the update was successful, false otherwise
         */
        @Override
        public boolean update(Category entity) {
            return false;
        }

        /**
         * Retrieves a list of all categories from the database.
         *
         * @return a list of Category objects
         */
        @Override
        public List<Category> getAllCategory() {
            String query = "SELECT * FROM Category";
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