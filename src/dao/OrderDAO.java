package dao;

import model.Order;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public int createOrder(int bookingId) {
        String sql = "INSERT INTO orders(booking_id, total, status) VALUES(?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, bookingId);
            ps.setDouble(2, 0);
            ps.setString(3, "PENDING");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateTotal(int orderId, double total) {
        String sql = "UPDATE orders SET total=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, total);
            ps.setInt(2, orderId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void completeOrder(int orderId) {
        String sql = "UPDATE orders SET status='DONE' WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("booking_id"),
                        rs.getInt("id"),
                        rs.getString("status"),
                        BigDecimal.valueOf(rs.getDouble("total"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public void updateStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Order> getByUser(int userId) {
        List<Order> list = new ArrayList<>();

        String sql = """
            SELECT o.* FROM orders o
            JOIN bookings b ON o.booking_id = b.id
            WHERE b.user_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("booking_id"),
                        rs.getInt("id"),
                        rs.getString("status"),
                        BigDecimal.valueOf(rs.getDouble("total"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}