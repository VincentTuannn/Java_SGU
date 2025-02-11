package JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JDBCConnect {
    
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Đăng ký MySQL Driver với DriverManager
            // Cấu hình thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/RUOU";
            String username = "root";
            String password = "";

            // Tạo kết nối
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // Xử lý SQLException
            e.printStackTrace();
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu!");
        } 

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


   
}