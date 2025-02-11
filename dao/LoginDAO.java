package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import JDBCUtil.JDBCConnect;
import view.Menu;

public class LoginDAO {
	
	    
    public static boolean checkPassword(String usrname, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String pwCharArray = null;

        try {
            // Assuming you have a valid connection object named "connection"
            // Replace "YourConnectionClass" with the actual class you are using for database connection.
            connection = JDBCConnect.getConnection();
            String query = "SELECT MATKHAU FROM account WHERE Email = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usrname);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pwCharArray = resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return password.equals(pwCharArray);
    }
    public static boolean isEmployee(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isEmployee = false;

        try {
            connection = JDBCConnect.getConnection();

            // Hash the password before comparing (replace this with your actual hashing logic)
            String query = "SELECT QUYEN FROM account WHERE EMAIL = ? AND MATKHAU = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String position = resultSet.getString("QUYEN");
                isEmployee = "NHÂN VIÊN".equals(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources(connection, preparedStatement, resultSet);
        }

        return isEmployee;
    }
    private static String hashPassword(char[] password) {

        return new String(password);
    }

    private static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
