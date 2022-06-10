package DAO;

import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User queries for database to ensure appropriate credentials and a valid login
 * @author Megan Riffey
 */

public class UserDAO {
    /**
     * Selecting and preparing the username and password from database
     * @param User_Name
     * @param Password
     * @return
     * @throws SQLException
     */
    public static boolean userLogin(String User_Name, String Password)  {
        try (PreparedStatement ps = JDBC.database().prepareStatement("SELECT * FROM Users WHERE  User_Name = ? AND  Password = ?")) {
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

    /**
     * Username validation, added BINARY to ensure username was case-sensitive and would not allow accidental login.
     * @param User_Name
     * @return
     * @throws SQLException
     */
    public static boolean usernameValidation(String User_Name)  {
        try (PreparedStatement ps = JDBC.database().prepareStatement("SELECT * FROM Users WHERE BINARY User_Name = ?")) {
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

    /**
     * Password Validation - added Binary to ensure the password was case-sensitive and would prevent accidental login with incorrect credentials.
     * @param Password
     * @return
     * @throws SQLException
     */
    public static boolean passwordValidation(String Password) throws SQLException {
        try (PreparedStatement ps = JDBC.database().prepareStatement("SELECT * FROM Users WHERE BINARY Password = ?")) {
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


