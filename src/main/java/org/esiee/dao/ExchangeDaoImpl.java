package org.esiee.dao;

import org.esiee.model.Exchange;
import org.esiee.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeDaoImpl implements ExchangeDao {

    private static final Logger LOGGER = Logger.getLogger(ExchangeDaoImpl.class.getName());

    /**
     * Saves a new Exchange entity to the database.
     *
     * @param entity the Exchange entity to be saved
     */
    @Override
    public void save(Exchange entity) {
        String query = "INSERT INTO Exchange (product_id_asked, product_id_offered, status) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getProductIdAsked());
            ps.setInt(2, entity.getProductIdOffered());
            ps.setString(3, entity.getStatus().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving exchange: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing Exchange entity in the database.
     *
     * @param entity the Exchange entity to be updated
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean update(Exchange entity) {
        String query = "UPDATE exchange SET status = ?, updated_at = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, entity.getStatus().toString());
            ps.setString(2, entity.getDateUpdated().toString());
            ps.setInt(3, entity.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating exchange: " + e.getMessage(), e);
        }
    }

    /**
     * Saves a new Exchange entity to the database.
     *
     * @param userId the Exchange entity to be saved
     */
    @Override
    public List<Exchange> getExchangesByUserId(int userId) {
        String query = "SELECT id, status, product_id_asked, product_id_offered, created_at, updated_at FROM exchange WHERE product_id_asked IN (SELECT id FROM Product WHERE user_id = ?) OR product_id_offered IN (SELECT id FROM Product WHERE user_id = ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            return getExchanges(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting exchanges by user id: " + e.getMessage(), e);
        }
    }

    private List<Exchange> getExchanges(PreparedStatement ps) throws SQLException {
        List<Exchange> exchanges = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                exchanges.add(new Exchange(
                        rs.getInt("id"),
                        rs.getInt("product_id_asked"),
                        rs.getInt("product_id_offered"),
                        Status.valueOf(rs.getString("status")),
                        new java.util.Date(rs.getTimestamp("created_at").getTime()),
                        new java.util.Date(rs.getTimestamp("updated_at").getTime())
                ));
            }
            return exchanges;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("Error getting exchanges: %s", e.getMessage()), e);
        }
        return exchanges;
    }

    /**
     * Retrieves an exchange by its ID.
     *
     * @param productId the ID of the exchange
     * @return the Exchange object with the specified ID
     */
    @Override
    public Exchange getExchangeById(int productId) {
        String query = "SELECT id, status, product_id_asked, product_id_offered, created_at, updated_at FROM exchange WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Exchange(
                            rs.getInt("id"),
                            rs.getInt("product_id_asked"),
                            rs.getInt("product_id_offered"),
                            Status.valueOf(rs.getString("status")),
                            new java.util.Date(rs.getTimestamp("created_at").getTime()),
                            new java.util.Date(rs.getTimestamp("updated_at").getTime())
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting exchanges by product id: " + e.getMessage(), e);
        }
        return null;
    }
}