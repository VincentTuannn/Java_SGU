/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import JDBCUtil.JDBCConnect;
import dto.VoucherDTO;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
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
public class voucherDAO implements DAOInterface<VoucherDTO> {

    public static voucherDAO getInstance() {
        return new voucherDAO();
    }

    @Override
    public int insert(VoucherDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "INSERT INTO `VOUCHER`(`TEN`,`GIATRI`,`NGAYBATDAU`,`NGAYKETTHUC`,`MOTA`,`TRANGTHAI`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTEN());
            pst.setDouble(2, t.getGIATRI());
            pst.setDate(3, (Date) t.getNGAYBATDAU());
            pst.setDate(4, (Date) t.getNGAYKETTHUC());
            pst.setString(5, t.getMOTA());
            pst.setInt(6, t.getTRANGTHAI());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    @Override
    public int update(VoucherDTO t) {
        int result = 0;
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "UPDATE `voucher` SET  `TEN`= ?,`GIATRI`= ?, `NGAYBATDAU`= ?, `NGAYKETTHUC`= ?, `MOTA`= ?, `TRANGTHAI`= ? WHERE `IDKHUYENMAI` = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, t.getTEN());
                pst.setDouble(2, t.getGIATRI());
                pst.setDate(3, (Date) t.getNGAYBATDAU());
                pst.setDate(4, (Date) t.getNGAYKETTHUC());
                pst.setString(5, t.getMOTA());
                pst.setInt(6, t.getTRANGTHAI());
                pst.setInt(7, t.getIDKHUYENMAI());
                result = pst.executeUpdate();
            }
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            // Log or print the exception details
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `VOUCHER` SET `TRANGTHAI` = 0 WHERE `IDKHUYENMAI`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<VoucherDTO> selectAll() {
        ArrayList<VoucherDTO> result = new ArrayList<VoucherDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM voucher";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDKHUYENMAI = rs.getInt("IDKHUYENMAI");
                String TEN = rs.getString("TEN");
                double GIATRI = rs.getDouble("GIATRI");
                Date NGAYBATDAU = rs.getDate("NGAYBATDAU");
                Date NGAYKETTHUC = rs.getDate("NGAYKETTHUC");
                String MOTA = rs.getString("MOTA");
                int TRANGTHAI = rs.getInt("TRANGTHAI");
                VoucherDTO product = new VoucherDTO(IDKHUYENMAI, TEN, GIATRI, NGAYBATDAU, NGAYKETTHUC, MOTA, TRANGTHAI);
                result.add(product);
            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public VoucherDTO selectById(int t) {
        VoucherDTO product = new VoucherDTO();
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "SELECT * FROM VOUCHER WHERE `IDKHUYENMAI`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t); // Thiết lập giá trị cho tham số productId
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int IDKHUYENMAI = rs.getInt("IDKHUYENMAI");
                String TEN = rs.getString("TEN");
                double GIATRI = rs.getDouble("GIATRI");
                Date NGAYBATDAU = rs.getDate("NGAYBATDAU");
                Date NGAYKETTHUC = rs.getDate("NGAYKETTHUC");
                String MOTA = rs.getString("MOTA");
                int TRANGTHAI = rs.getInt("TRANGTHAI");
                product = new VoucherDTO(IDKHUYENMAI, TEN, GIATRI, NGAYBATDAU, NGAYKETTHUC, MOTA, TRANGTHAI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public ArrayList<VoucherDTO> selectByCondition(String condition) {
        return null;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND   TABLE_NAME   = 'voucher'";
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
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int restore(VoucherDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `VOUCHER` SET `TRANGTHAI`=1 WHERE `IDKHUYENMAI`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDKHUYENMAI());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<VoucherDTO> selectAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
