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

/**
 * Appointment SQL queries to obtain appointment information from database
 *
 * @author Megan Riffey
 */
public class AppointmentDAO {

    /**
     * SQL Query to obtain an observables list of all appointments in the database.
     *
     * @return appointmentList
     */
    public static ObservableList<Appointment> getAppointmentList() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");


                Appointment c = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                appointmentList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    /**
     * SQL query to update the selected modified appointment in the database
     *
     * @param appointmentId          appointment ID
     * @param appointmentTitle       appointment title
     * @param appointmentDescription appointment description
     * @param appointmentContact     appointment contact
     * @param appointmentType        appointment type
     * @param appointmentStart       appointment start date/time
     * @param appointmentEnd         appointment end date/time
     * @param appointmentCustomerId  associated appointment customer ID
     * @param appointmentUserId      associated appointment user ID
     * @param appointmentLocation    appointment location
     */
    public static void updateAppointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, int appointmentContact) {
        try {

            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement updateAppointment = JDBC.conn.prepareStatement(sql);


            updateAppointment.setString(1, appointmentTitle);
            updateAppointment.setString(2, appointmentDescription);
            updateAppointment.setString(3, appointmentLocation);
            updateAppointment.setString(4, appointmentType);
            updateAppointment.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            updateAppointment.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            updateAppointment.setInt(7, appointmentCustomerId);
            updateAppointment.setInt(8, appointmentUserId);
            updateAppointment.setInt(9, appointmentContact);
            updateAppointment.setInt(10, appointmentId);

            updateAppointment.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SQL query to add new appointment into the database. Database auto-increments appointment ID.
     *
     * @param appointmentTitle       appointment title
     * @param appointmentDescription appointment description
     * @param appointmentLocation    appointment location
     * @param appointmentType        appointment Type
     * @param appointmentStart       appointment Start date/time
     * @param appointmentEnd         appointment End date/time
     * @param appointmentCustomerId  associated appointment customer ID
     * @param appointmentUserId      associated appointment user ID
     * @param appointmentContact     associated appointment contact ID
     * @throws SQLException addresses unhandled SQL exception
     */
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

    /**
     * SQL Query to get weekly appointments for the next 7 days and add to ObservableList.
     *
     * @return weeklyList
     */
    public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weeklyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE YEARWEEK(START) = YEARWEEK(NOW()) ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                weeklyList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weeklyList;
    }

    /**
     * SQL query to delete appointments from database by appointment ID.
     *
     * @param appointmentId appointment ID
     */
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

    /**
     * SQL Query to select monthly appointments
     *
     * @return monthlyList
     */
    public static ObservableList<Appointment> getMonthlyAppointments() {
        ObservableList<Appointment> monthlyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE MONTH(START) = MONTH(NOW()) ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment weekly = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                monthlyList.add(weekly);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monthlyList;
    }

    /**
     * SQL query to get appointments associated with user during login to display appointments within 15 minutes
     *
     * @param userId user ID
     * @return userAppointments
     */
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

    /**
     * SQL query to get associated appointments for contacts - used in reports
     *
     * @param contactId contact ID
     * @return contactAppointment
     */
    public static ObservableList<Appointment> getContactAppointment(int contactId) {
        ObservableList<Appointment> contactAppointment = FXCollections.observableArrayList();
        try {
            String sqlStatement = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID  = '" + contactId + "'";
            PreparedStatement ps = JDBC.conn.prepareStatement(sqlStatement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentContactName = rs.getString("Contact_Name");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int appointmentCustomerId = rs.getInt("Customer_ID");
                int appointmentUserId = rs.getInt("User_ID");
                String appointmentLocation = rs.getString("Location");
                Appointment results = new Appointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentContactName,
                        appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
                contactAppointment.add(results);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactAppointment;
    }

    /**
     * SQL query to count appointment types and provide distinct results for reports table
     *
     * @return appointmentListType
     */
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

    /**
     * SQL query to get the month and number of appointments per month for reports
     *
     * @return appointmentTypeMonthTotal
     */
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