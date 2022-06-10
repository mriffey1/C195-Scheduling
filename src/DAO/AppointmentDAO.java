package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentDAO {

    public static ObservableList<Appointment> getAppointmentList() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, appointments.Location," +
                    "appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");
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

    public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weeklyList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Type, appointments.Description, appointments.Location," +
                    "appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID FROM appointments WHERE appointments.Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 7 DAY)";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                int appointmentContact = rs.getInt("Contact_ID");
                String appointmentType = rs.getString("Type");
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");
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


}

