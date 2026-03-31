package dao;

import model.PC;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PCDAO {


    public List<PC> getAll() {
        List<PC> list = new ArrayList<>();
        String sql = "SELECT * FROM pcs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PC pc = new PC(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price_per_hour"),
                        rs.getString("status"),
                        rs.getString("type")
                );
                list.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public PC findById(int id) {
        String sql = "SELECT * FROM pcs WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new PC(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price_per_hour"),
                        rs.getString("status"),
                        rs.getString("type")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean insert(PC pc) {
        String sql = "INSERT INTO pcs(name, type, price_per_hour, status) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pc.getName());
            ps.setString(2, pc.getType());
            ps.setBigDecimal(3, pc.getPricePerHour());
            ps.setString(4, pc.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(PC pc) {
        String sql = "UPDATE pcs SET name=?, type=?, price_per_hour=?, status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pc.getName());
            ps.setString(2, pc.getType());
            ps.setBigDecimal(3, pc.getPricePerHour());
            ps.setString(4, pc.getStatus());
            ps.setInt(5, pc.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateStatus(int pcId, String status) {
        String sql = "UPDATE pcs SET status=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, pcId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean delete(int id) {
        String sql = "DELETE FROM pcs WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}