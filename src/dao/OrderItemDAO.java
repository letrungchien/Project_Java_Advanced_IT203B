package dao;

import util.DBConnection;

import java.sql.*;

public class OrderItemDAO {

    // kiểm tra món đã tồn tại trong order chưa
    public boolean exists(int orderId, int foodId) {
        String sql = "SELECT * FROM order_items WHERE order_id=? AND food_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, foodId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // thêm mới
    public void insert(int orderId, int foodId, int qty, double price) {
        String sql = "INSERT INTO order_items(order_id, food_id, quantity, price) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, foodId);
            ps.setInt(3, qty);
            ps.setDouble(4, price);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update nếu đã tồn tại
    public void updateQty(int orderId, int foodId, int qty) {
        String sql = "UPDATE order_items SET quantity = quantity + ? WHERE order_id=? AND food_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setInt(2, orderId);
            ps.setInt(3, foodId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}