package org.esiee.dao;

import org.esiee.model.Exchange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ExchangeDaoImpl implements ExchangeDao {
    @Override
    public void save(Exchange entity) {
        String query = "INSERT INTO exchange (item_id_asked, item_id_offered, status, date_created, date_updated) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, entity.getItem_id_asked());
                ps.setInt(2, entity.getItem_id_offered());
                ps.setString(3, entity.getStatus());
                ps.setString(4, "date"); //TODO : Date
                ps.setString(5, entity.getDate_updated()); //TODO : Date
                ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving exchange: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Exchange entity) {
        String query = "UPDATE exchange SET status = ?, date_updated = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, entity.getStatus());
                ps.setString(2, entity.getDate_updated()); //TODO : Date
                ps.setInt(3, entity.getId());
                return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating exchange: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Exchange> getExchangesByUserId(int userId) {
        String query = "SELECT * FROM exchange WHERE item_id_asked IN (SELECT id FROM item WHERE userId = ?) OR item_id_offered IN (SELECT id FROM item WHERE userId = ?)";
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
                        rs.getInt("item_id_asked"),
                        rs.getInt("item_id_offered"),
                        rs.getString("status"),
                        rs.getString("date_created"),
                        rs.getString("date_updated")
                ));
            }
            return exchanges;
        }
    }
}
