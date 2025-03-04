package org.esiee.dao;

import org.esiee.model.Exchange;
import org.esiee.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExchangeDaoImpl implements ExchangeDao {
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

    @Override
    public boolean update(Exchange entity) {
        String query = "UPDATE exchange SET status = ?, updated_at = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, entity.getStatus().toString());
                ps.setString(2, entity.getDateUpdated().toString()); //TODO: fix
                ps.setInt(3, entity.getId());
                return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating exchange: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Exchange> getExchangesByUserId(int userId) {
        String query = "SELECT * FROM exchange WHERE product_id_asked IN (SELECT id FROM Product WHERE user_id = ?) OR product_id_offered IN (SELECT id FROM Product WHERE user_id = ?)";
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
        }
        catch (Exception e) {
            System.out.println("Error getting exchanges: " + e.getMessage());
        }
        return exchanges;
    }
}
