/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import JDBCUtil.JDBCConnect;
import dto.PHUONGTHUCTHANHTOANDTO;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lap4all
 */
public class PHUONGTHUCTHANHTOANDAO implements DAOInterface<PHUONGTHUCTHANHTOANDTO> {

    public static PHUONGTHUCTHANHTOANDAO getInstance() {
        return new PHUONGTHUCTHANHTOANDAO();
    }
    @Override
    public int insert(PHUONGTHUCTHANHTOANDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "INSERT INTO `phuongthucthanhtoan`(`TEN`,`status`) VALUES (?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTEN());;
            pst.setInt(2, t.getStatus());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
        }
        return result;    }

    @Override
    public int update(PHUONGTHUCTHANHTOANDTO t) {
        int result = 0;
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "UPDATE `phuongthucthanhtoan` SET  `TEN`= ?,`status`= ? WHERE `IDTHANHTOAN` = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, t.getTEN());
                pst.setInt(2, t.getStatus());
                pst.setInt(3, t.getIDTHANHTOAN());
                result = pst.executeUpdate();
            }
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            // Log or print the exception details
            ex.printStackTrace();
        }
        return result;    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `phuongthucthanhtoan` SET `status` = 0 WHERE `IDTHANHTOAN`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
        }
        return result;
    }

    @Override
    public ArrayList<PHUONGTHUCTHANHTOANDTO> selectAll() {
        ArrayList<PHUONGTHUCTHANHTOANDTO> result = new ArrayList<PHUONGTHUCTHANHTOANDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM phuongthucthanhtoan ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDTHANHTOAN = rs.getInt("IDTHANHTOAN");
                String TEN = rs.getString("TEN");
                int STATUS = rs.getInt("status");
                PHUONGTHUCTHANHTOANDTO lh = new PHUONGTHUCTHANHTOANDTO(IDTHANHTOAN, TEN, STATUS);
                result.add(lh);
            }
            JDBCConnect.closeConnection(con);
        } catch (Exception e) {

        }
        return result;

    }

    @Override
    public PHUONGTHUCTHANHTOANDTO selectById(int t) {
        PHUONGTHUCTHANHTOANDTO thanhtoan = new PHUONGTHUCTHANHTOANDTO();
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "SELECT * FROM phuongthucthanhtoan WHERE `IDTHANHTOAN`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t); // Thiết lập giá trị cho tham số productId
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int IDTHANHTOAN = rs.getInt("IDTHANHTOAN");
                String TEN = rs.getString("TEN");
                int status = rs.getInt("status");
                thanhtoan = new PHUONGTHUCTHANHTOANDTO(IDTHANHTOAN, TEN, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thanhtoan;
    }

    @Override
    public ArrayList<PHUONGTHUCTHANHTOANDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND   TABLE_NAME   = 'phuongthucthanhtoan'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
        }
        return result;
    }

    @Override
    public int restore(PHUONGTHUCTHANHTOANDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `phuongthucthanhtoan` SET `TRANGTHAI`=1 WHERE `IDTHANHTOAN`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDTHANHTOAN());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
        }
        return result;
    }

    @Override
    public ArrayList<PHUONGTHUCTHANHTOANDTO> selectAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}


