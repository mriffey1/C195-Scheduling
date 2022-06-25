package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User queries for database to ensure appropriate credentials and a valid login
 *
 * @author Megan Riffey
 */

public class UserDAO {
    /**
     * SQL Query to get all users and add to an observablelist
     *
     * @return userList
     */
    public static ObservableList<User> getUserList() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");

                User u = new User(userId, userName);
                userList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    /**
     * Selecting and preparing the username and password from database
     *
     * @param User_Name user's username
     * @param Password user's password
     * @return true/false
     */
    public static boolean userLogin(String User_Name, String Password) {
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
     *
     * @param User_Name user's username
     * @return true/false
     */
    public static boolean usernameValidation(String User_Name) {
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
     *
     * @param Password user's password
     * @return true/false
     */
    public static boolean passwordValidation(String Password)  {
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

    /**
     * SQL Query to get userId based on associated userName
     *
     * @param userName username
     * @return userId
     * @throws SQLException addresses unhandled SQL exception
     */
    public static int getUserId(String userName) throws SQLException {
        int userId = 0;
        String sqlStatement = "select User_ID, User_Name from users where User_Name = '" + userName + "'";
        PreparedStatement ps = JDBC.conn.prepareStatement(sqlStatement);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            userId = rs.getInt("User_ID");
            userName = rs.getString("User_Name");
        }
        return userId;
    }

    /**
     * returns username based on user Id
     *
     * @param userId user id
     * @return u - userId and username
     */
    public static User returnUserId(int userId) {
        try {
            String sql = "SELECT User_ID, User_Name FROM users WHERE User_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedUserId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            User u = new User(searchedUserId, userName);
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




