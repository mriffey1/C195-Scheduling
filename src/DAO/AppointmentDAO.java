package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDAO {

    public static ObservableList<Appointment> getAppointmentList() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");

                Appointment c = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                appointmentList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public static void updateAppointment(int appointmentId, String appointmentTitle, String appointmentDescription, int appointmentContact, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, String appointmentLocation) {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ? , Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement updateAppointment = JDBC.conn.prepareStatement(sql);
            ResultSet rs = updateAppointment.executeQuery();
            while (rs.next()) {
                updateAppointment.setInt(1, appointmentId);
                updateAppointment.setString(2, appointmentTitle);
                updateAppointment.setString(3, appointmentDescription);
                updateAppointment.setInt(4, appointmentContact);
                updateAppointment.setString(5, appointmentType);
                updateAppointment.setTimestamp(6, Timestamp.valueOf(appointmentStart));
                updateAppointment.setTimestamp(7, Timestamp.valueOf(appointmentEnd));
                updateAppointment.setInt(8, appointmentCustomerId);
                updateAppointment.setInt(9, appointmentUserId);
                updateAppointment.setString(10, appointmentLocation);
                updateAppointment.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // return appointmentList;

    public static void addAppointment(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, int appointmentContact) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertAppoint = JDBC.conn.prepareStatement(sql);

        insertAppoint.setString(1, appointmentTitle);
        insertAppoint.setString(2, appointmentDescription);
        insertAppoint.setString(3, appointmentLocation);
        insertAppoint.setString(4, appointmentType);
        insertAppoint.setTimestamp(5, Timestamp.valueOf(appointmentStart));
        insertAppoint.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
        insertAppoint.setInt(7, appointmentCustomerId);
        insertAppoint.setInt(8, appointmentUserId);
        insertAppoint.setInt(9, appointmentContact);

        insertAppoint.executeUpdate();

    }


    public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weeklyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * from appointments WHERE Start >= NOW() + 7";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                weeklyList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weeklyList;
    }

    public static void deleteAppointment(int appointmentId) {
        try {
            String sqldelete = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement deleteAppoint = JDBC.conn.prepareStatement(sqldelete);
            deleteAppoint.setInt(1, appointmentId);
            deleteAppoint.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Appointment> getMonthlyAppointments() {
        ObservableList<Appointment> monthlyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(NOW())";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                monthlyList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monthlyList;
    }

    public static ObservableList<Appointment> getUserAppointments(int userId) {
        ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM Appointments WHERE User_ID  = '" + userId + "'";
            PreparedStatement ps = JDBC.conn.prepareStatement(sqlStatement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment results = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                userAppointments.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAppointments;
    }

    public static ObservableList<Appointment> getContactAppointment(int contactId) {
        ObservableList<Appointment> contactAppointment = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM Appointments WHERE Contact_ID  = '" + contactId + "'";
            PreparedStatement ps = JDBC.conn.prepareStatement(sqlStatement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment results = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                contactAppointment.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactAppointment;
    }

    public static ObservableList<Appointment> appointmentById(int customerId) {
        ObservableList<Appointment> customerAppointmentList = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM Appointments WHERE Customer_ID  = '" + customerId + "'";
            PreparedStatement ps = JDBC.conn.prepareStatement(sqlStatement);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment results = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                customerAppointmentList.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerAppointmentList;
    }

    public static ObservableList<Appointment> appointmentType() {
        ObservableList<Appointment> appointmentListType = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Type, Count(*) AS NUM FROM appointments GROUP BY Type";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String appointmentType = rs.getString("Type");
                 int appointmentTypeTotal = rs.getInt("NUM");
                Appointment results = new Appointment(appointmentType, appointmentTypeTotal);
                appointmentListType.add(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentListType;
    }

    public static ObservableList<Appointment> getAppointmentTypeMonth() {
        ObservableList<Appointment> appointmentTypeMonthTotal = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT(MONTHNAME(Start)) AS Month, Count(*) AS NUM FROM appointments GROUP BY Month";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String appointmentType = rs.getString("Month");
                int appointmentTypeTotal = rs.getInt("NUM");
                Appointment results = new Appointment(appointmentType, appointmentTypeTotal);
                appointmentTypeMonthTotal.add(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentTypeMonthTotal;
    }


}


