package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import JDBCUtil.JDBCConnect;
import dto.importDTO;
import dto.detailimportDTO;
import java.sql.Timestamp;
import dto.SupplierDTO;
import java.sql.Date;

public class importDAO implements DAOInterface<importDTO> {

    public static importDAO getInstance() {
        return new importDAO();
    }

    @Override
    public int insert(importDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "INSERT INTO `import`(`IDIM`,`IDNCC`, `IDTAIKHOAN`, `NGAYNHAPKHO`, `TONGGIATRI`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDIM());
            pst.setInt(2, t.getIDNCC());
            pst.setInt(3, t.getIDTAIKHOAN());
            pst.setDate(4, (Date) t.getNGAYNHAPKHO());
            pst.setDouble(5, t.getTONGGIATRI());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(importDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(importDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `import` SET `TRANGTHAI` = 0 WHERE `IDIM`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDIM());
            pst.setInt(2, t.getIDNCC());
            pst.setInt(3, t.getIDTAIKHOAN());
            pst.setDate(4, (Date) t.getNGAYNHAPKHO());
            pst.setDouble(5, t.getTONGGIATRI());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(importDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `import` SET `TRANGTHAI` = 0 WHERE `IDIM`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(importDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<importDTO> selectAll() {
        ArrayList<importDTO> result = new ArrayList<importDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM import";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDIM = rs.getInt("IDIM");
                int IDNCC = rs.getInt("IDNCC");
                int IDTAIKHOAN = rs.getInt("IDTAIKHOAN");
                Date NGAYNHAPKHO = rs.getDate("NGAYNHAPKHO");
                double TONGGIATRI = rs.getDouble("TONGGIATRI");
                importDTO Import = new importDTO(IDIM, IDNCC, IDTAIKHOAN, NGAYNHAPKHO, TONGGIATRI);
                result.add(Import);

            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public importDTO selectById(int t) {
        importDTO Import = new importDTO();
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "SELECT * FROM import WHERE `IDIM`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t); // Thiết lập giá trị cho tham số productId
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int IDIM = rs.getInt("IDIM");
                int IDNCC = rs.getInt("IDNCC");
                int IDTAIKHOAN = rs.getInt("IDTAIKHOAN");
                Date NGAYNHAPKHO = rs.getDate("NGAYNHAPKHO");
                double TONGGIATRI = rs.getDouble("tONGGIATRI");
                Import = new importDTO(IDIM, IDNCC, IDTAIKHOAN, NGAYNHAPKHO, TONGGIATRI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Import;
    }

    @Override
    public ArrayList<importDTO> selectByCondition(String condition) {
        return null;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
    try {
        Connection con = (Connection) JDBCConnect.getConnection();
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND  TABLE_NAME  = 'import'"; // Update 'product' to 'receipt'
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
    public int restore(importDTO t) {
        int result = 0;
    try {
        Connection con = (Connection) JDBCConnect.getConnection();
        String sql = "UPDATE `import` SET `status`=1 WHERE `IDIM`=?"; 
        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
        pst.setInt(1, t.getIDIM()); 
        result = pst.executeUpdate();
        JDBCConnect.closeConnection(con);
    } catch (SQLException ex) {
        Logger.getLogger(importDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result;
    }

    @Override
    public ArrayList<importDTO> selectAll(String t) {
        ArrayList<importDTO> result = new ArrayList<importDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM import ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDIM = rs.getInt("IDIM");
                int IDNCC = rs.getInt("IDNCC");
                int IDTAIKHOAN = rs.getInt("IDTAIKHOAN");
                Date NGAYNHAPKHO = rs.getDate("NGAYNHAPKHO");
                double TONGGIATRI = rs.getDouble("TONGGIATRI");
                importDTO Import = new importDTO(IDIM, IDNCC, IDTAIKHOAN, NGAYNHAPKHO, TONGGIATRI);
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
