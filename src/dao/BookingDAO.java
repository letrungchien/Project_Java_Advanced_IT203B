package dao;

import model.Booking;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean isPCBooked(int pcId, String start, String end) {
        String sql = "SELECT * FROM bookings WHERE pc_id = ? " +
                "AND (start_time < ? AND end_time > ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pcId);
            ps.setString(2, end);
            ps.setString(3, start);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insert(int userId, int pcId, String start, String end) {
        String sql = "INSERT INTO bookings(user_id, pc_id, start_time, end_time, status) VALUES(?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setInt(2, pcId);
            ps.setString(3, start);
            ps.setString(4, end);
            ps.setString(5, "PENDING");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public List<Booking> getAll() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking(
                        rs.getTimestamp("end_time").toLocalDateTime(),
                        rs.getInt("id"),
                        rs.getInt("pc_id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getString("status"),
                        rs.getInt("user_id")
                );
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public void updateStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, bookingId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Booking> getByUser(int userId) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Booking(
                        rs.getTimestamp("end_time").toLocalDateTime(),
                        rs.getInt("id"),
                        rs.getInt("pc_id"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getString("status"),
                        userId
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}