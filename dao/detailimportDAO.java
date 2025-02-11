/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import JDBCUtil.JDBCConnect;
import dto.detailimportDTO;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lap4all
 */
public class detailimportDAO implements DAOInterface<detailimportDTO>{

  public static importDAO getInstance() {
        return new importDAO();
    }

    @Override
    public int insert(detailimportDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "INSERT INTO `detailimport`(`IDPHIEUNHAP`,`IDLOHANG`, `SOLUONG`, `DONGIANHAP`, `TONGTIEN`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDPHIEUNHAP());
            pst.setInt(2, t.getIDLOHANG());
            pst.setInt(3, t.getSOLUONG());
            pst.setDouble(4, t.getDONGIANHAP());
            pst.setDouble(5, t.getTONGTIEN());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(detailimportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(detailimportDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `detailimport` SET `TRANGTHAI` = 0 WHERE `IDPHIEUNHAP`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDPHIEUNHAP());
            pst.setInt(2, t.getIDLOHANG());
            pst.setInt(3, t.getSOLUONG());
            pst.setDouble(4, t.getDONGIANHAP());
            pst.setDouble(5, t.getTONGTIEN());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(detailimportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `detailimport` SET `TRANGTHAI` = 0 WHERE `IDPHIEUNHAP`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(detailimportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<detailimportDTO> selectAll() {
        ArrayList<detailimportDTO> result = new ArrayList<detailimportDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM detailimport";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDPHIEUNHAP = rs.getInt("IDPHIEUNHAP");
                int IDLOHANG = rs.getInt("IDLOHANG");
                int SOLUONG = rs.getInt("SOLUONG");
                double DONGIANHAP = rs.getDouble("DONGIANHAP");
                double TONGTIEN = rs.getDouble("TONGTIEN");
                detailimportDTO Import = new detailimportDTO(IDPHIEUNHAP, IDLOHANG, SOLUONG, DONGIANHAP, TONGTIEN);
                result.add(Import);

            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public detailimportDTO selectById(int t) {
        detailimportDTO Import = new detailimportDTO();
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "SELECT * FROM detailimport WHERE `IDPHIEUNHAP`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t); // Thiết lập giá trị cho tham số productId
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int IDPHIEUNHAP = rs.getInt("IDPHIEUNHAP");
                int IDLOHANG = rs.getInt("IDLOHANG");
                int SOLUONG = rs.getInt("SOLUONG");
                double DONGIANHAP = rs.getDouble("DONGIANGAP");
                double TONGTIEN = rs.getDouble("tONGTIEN");
                Import = new detailimportDTO(IDPHIEUNHAP, IDLOHANG, SOLUONG, DONGIANHAP, TONGTIEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Import;
    }

    @Override
    public ArrayList<detailimportDTO> selectByCondition(String condition) {
        return null;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
    try {
        Connection con = (Connection) JDBCConnect.getConnection();
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND  TABLE_NAME  = 'detailimport'"; // Update 'product' to 'receipt'
        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
        ResultSet rs2 = pst.executeQuery(sql);
        if (!rs2.isBeforeFirst()) {
            System.out.println("No data found for import table");
        } else {
            while (rs2.next()) {
                result = rs2.getInt("AUTO_INCREMENT");
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(importDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result;
    }

    @Override
    public int restore(detailimportDTO t) {
        int result = 0;
    try {
        Connection con = (Connection) JDBCConnect.getConnection();
        String sql = "UPDATE `detailimport` SET `status`=1 WHERE `IDPHIEUNHAP`=?"; 
        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
        pst.setInt(1, t.getIDPHIEUNHAP()); 
        result = pst.executeUpdate();
        JDBCConnect.closeConnection(con);
    } catch (SQLException ex) {
        Logger.getLogger(detailimportDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result;
    }

    @Override
    public ArrayList<detailimportDTO> selectAll(String t) {
        ArrayList<detailimportDTO> result = new ArrayList<detailimportDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM detailimport ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDPHIEUNHAP = rs.getInt("IDPHIEUNHAP");
                int IDLOHANG = rs.getInt("IDLOHANG");
                int SOLUONG = rs.getInt("SOLUONG");
                double DONGIANHAP = rs.getDouble("DONGIANHAP");
                double TONGTIEN = rs.getDouble("TONGTIEN");
                detailimportDTO Import = new detailimportDTO(IDPHIEUNHAP, IDLOHANG, SOLUONG, DONGIANHAP, TONGTIEN);
                result.add(Import);
            }
            JDBCConnect.closeConnection(con);
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
