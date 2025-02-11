package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.AccountDTO;
import bus.AccountBUS;
import JDBCUtil.JDBCConnect;
import java.sql.DriverManager;


public class AccountDAO implements DAOInterface<AccountDTO>{
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcMATKHAU;
    private Connection jdbcConnection;

    public AccountDAO(String jdbcURL, String jdbcUsername, String jdbcMATKHAU) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcMATKHAU = jdbcMATKHAU;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcMATKHAU);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertAccount(AccountDTO account) throws SQLException {
        String sql = "INSERT INTO account (SODIENTHOAI, HOTEN, GIOITINH, MATKHAU, EMAIL, NGAYSINH, DIACHI) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, account.getSODIENTHOAI());
        statement.setString(2, account.getHOTEN());
        statement.setString(3, String.valueOf(account.getGIOITINH()));
        statement.setString(4, account.getMATKHAU());
        statement.setString(5, account.getEMAIL());
        statement.setDate(6, new java.sql.Date(account.getNGAYSINH().getTime()));
        statement.setString(7, account.getDIACHI());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    public boolean updateAccount(AccountDTO account) throws SQLException {
    String sql = "UPDATE account SET SODIENTHOAI = ?, HOTEN = ?, GIOITINH = ?, MATKHAU = ?, EMAIL = ?, NGAYSINH = ?, DIACHI = ?, TRANGTHAI = ? WHERE IDTAIKHOAN = ?";
    connect();

    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    statement.setString(1, account.getSODIENTHOAI());
    statement.setString(2, account.getHOTEN());
    statement.setString(3, String.valueOf(account.getGIOITINH()));
    statement.setString(4, account.getMATKHAU());
    statement.setString(5, account.getEMAIL());
    statement.setDate(6, new java.sql.Date(account.getNGAYSINH().getTime()));
    statement.setString(7, account.getDIACHI());
    statement.setString(8, String.valueOf(account.getTRANGTHAI()));
    statement.setInt(9, account.getIDTAIKHOAN());

    boolean rowUpdated = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowUpdated;
}

// Method to delete an account from the database
public boolean deleteAccount(int IDTAIKHOAN) throws SQLException {
    String sql = "DELETE FROM account WHERE IDTAIKHOAN = ?";
    connect();

    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    statement.setInt(1, IDTAIKHOAN);

    boolean rowDeleted = statement.executeUpdate() > 0;
    statement.close();
    disconnect();
    return rowDeleted;
}
    // Methods for updateAccount, deleteAccount, getAccount, and listAllaccount would be similar
    // Implement them based on your specific requirements and database schema

//    @Override
//    public int insert(InvoiceDTO t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    @Override
//    public int update(InvoiceDTO t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int delete(String t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<InvoiceDTO> selectAll() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public InvoiceDTO selectById(String t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<InvoiceDTO> selectByCondition(String condition) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int getAutoIncrement() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int restore(InvoiceDTO t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public ArrayList<InvoiceDTO> selectAll(String t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void importDatabase(FileInputStream inputStream) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public boolean insertAccount(Account account) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public int delete(int t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public InvoiceDTO selectById(int t) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public int insert(AccountDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(AccountDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AccountDTO> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AccountDTO selectById(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AccountDTO> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int restore(AccountDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AccountDTO> selectAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void importDatabase(FileInputStream inputStream) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}