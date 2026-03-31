package dao;

import model.User;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;

public class UserDAO {

    public User findByUsername(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        BigDecimal.valueOf(rs.getDouble("balance")),
                        rs.getInt("id"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("username")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean register(String username, String password, BigDecimal balance) {
        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO users(username, password, role, balance) VALUES (?, ?, 'CUSTOMER', ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBigDecimal(3, balance);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}