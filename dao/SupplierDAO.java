package dao;

import JDBCUtil.JDBCConnect;
import dto.SupplierDTO;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SupplierDAO implements DAOInterface<SupplierDTO>{

     public static SupplierDAO getInstance() {
        return new SupplierDAO();
    }
    
    @Override
    public int insert(SupplierDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "INSERT INTO `supplier`(`IDSU`, `EMAIL`, `TEN`, `DIACHI`, `SODIENTHOAI`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            int getIDSU = 0;
            pst.setInt(1, getIDSU);
            String getEMAIL = null;
            pst.setString(2, getEMAIL);
            String getTEN = null;
            pst.setString(3, getTEN);
            String getDIACHI = null;
            pst.setString(4, getDIACHI);
            int getSODIENTHOAI = 0;
            pst.setInt(5, getSODIENTHOAI);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
        }
        return result;
    }

    @Override
    public int update(SupplierDTO t) {
        int result = 0;
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "INSERT INTO `supplier`(`IDSU`, `EMAIL`, `TEN`, `DIACHI`, `SODIENTHOAI`) VALUES (?,?,?,?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                int getIDSU = 0;

            pst.setInt(1, getIDSU);
                String getEMAIL = null;
            pst.setString(2, getEMAIL);
             String getTEN = null;
            pst.setString(3, getTEN);
                String getDIACHI = null;
            pst.setString(4, getDIACHI);
                int getSODIENTHOAI = 0;
            pst.setInt(5, getSODIENTHOAI);
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
            String sql = "UPDATE `supplier` SET `status` = 0 WHERE `IDSU`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<SupplierDTO> selectAll() {
         ArrayList<SupplierDTO> result = new ArrayList<SupplierDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM supplier";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDSU = rs.getInt("IDSU");
                String EMAIL = rs.getString("EMAIL");              
                String SODIENTHOAI = rs.getString("SODIENTHOAI");               
                String DIACHI = rs.getString("DIACHI");
                String TEN = rs.getString("TEN");
                int status = rs.getInt("status");
                SupplierDTO supplier = new SupplierDTO(IDSU, EMAIL, SODIENTHOAI, DIACHI, TEN,status);
                result.add(supplier);

            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public SupplierDTO selectById(int t) {
         SupplierDTO supplier = new SupplierDTO();
        try {
            Connection con = JDBCConnect.getConnection();
            String sql = "SELECT * FROM supplier WHERE `IDSU`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t); // Thiết lập giá trị cho tham số productId
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int IDSU = rs.getInt("IDSU");
                String EMAIL = rs.getString("EMAIL");
                String SODIENTHOAI = rs.getString("SODIENTHOAI");
                String DIACHI = rs.getString("DIACHI");
                String TEN = rs.getString("TEN");
                supplier = new SupplierDTO(IDSU, EMAIL, SODIENTHOAI, DIACHI, TEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public ArrayList<SupplierDTO> selectByCondition(String condition) {
          return null;
    }

    @Override
    public int getAutoIncrement() {
       int result = -1;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'RUOU' AND   TABLE_TEN   = 'supplier'";
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
    public int restore(SupplierDTO t) {
         int result = 0;
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "UPDATE `supplier` SET `status`=1 WHERE `IDSU`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getIDSU());
            result = pst.executeUpdate();
            JDBCConnect.closeConnection(con);
        } catch (SQLException ex) {
        }
        return result;
    }



//    @Override
//    public ArrayList<SupplierDTO> selectAll(String t) {
//         return null;
//    }
//
//    @Override
//    public void importDatabase(FileInputStream inputStream) {
//    }
//
//    @Override
//    public ArrayList<SupplierDTO> selectAll() {
//      
//    }
//

//    @Override
//    public ArrayList<SupplierDTO> selectAll(String t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public SupplierDTO selectById(int t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<SupplierDTO> selectByCondition(String condition) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int update(SupplierDTO t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int restore(SupplierDTO t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }   

    @Override
    public ArrayList<SupplierDTO> selectAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
