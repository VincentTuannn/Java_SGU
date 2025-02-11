/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import JDBCUtil.JDBCConnect;
import dto.lohangDTO;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Lap4all
 */
public class lohangDAO implements DAOInterface<lohangDTO> {

    @Override
    public int insert(lohangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(lohangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<lohangDTO> selectAll() {
        ArrayList<lohangDTO> result = new ArrayList<lohangDTO>();
        try {
            Connection con = (Connection) JDBCConnect.getConnection();
            String sql = "SELECT * FROM lohang ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int IDLOHANG = rs.getInt("IDLOHANG");
                Date NGAYSANXUAT = rs.getDate("NGAYSANXUAT");
                int HANSUDUNG = rs.getInt("HANSUDUNG");
                int SOLUONG = rs.getInt("SOLUONG");
                int IDSANPHAM = rs.getInt("IDSANPHAM");
                lohangDTO lh = new lohangDTO(IDLOHANG, NGAYSANXUAT, HANSUDUNG, SOLUONG, IDSANPHAM);
                result.add(lh);
            }
            JDBCConnect.closeConnection(con);
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public lohangDTO selectById(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<lohangDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int restore(lohangDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<lohangDTO> selectAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
