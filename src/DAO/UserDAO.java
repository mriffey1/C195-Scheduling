package DAO;

import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean userLogin(String User_Name, String Password) throws SQLException {

        try (PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM Users WHERE  User_Name = ? AND  Password = ?")) {
            ps.setString(1, User_Name);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean usernameValidation(String User_Name) throws SQLException {
        try (PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM Users WHERE BINARY User_Name = ?")) {
            ps.setString(1, User_Name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean passwordValidation(String Password) throws SQLException {
        try (PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM Users WHERE BINARY Password = ?")) {
            ps.setString(1, Password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}


