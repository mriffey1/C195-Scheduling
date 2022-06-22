package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static java.time.LocalDateTime.now;

public class AppointmentsModify implements Initializable {

    public TextField appointmentIDTextField;
    public TextField appointmentTitleTextField;
    public TextField appointmentDescriptionTextField;
    public TextField appointmentLocationTextField;
    public TextField appointmentTypeTextField;
    public ComboBox<Contact> contactCombo;
    public Button saveButton;
    public Button cancelButton;
    public DatePicker startDatePicker;
    public ComboBox<LocalTime> startTimeCombo;
    public DatePicker endDatePicker;
    public ComboBox<LocalTime> endTimeCombo;
    public ComboBox<User> userCombo;
    public ComboBox<Customer> customerCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contactList = ContactDAO.getAllContacts();
        contactCombo.setItems(contactList);
        contactCombo.setVisibleRowCount(10);

        ObservableList<Customer> customerList = CustomerDAO.getCustomerList();
        customerCombo.setItems(customerList);
        customerCombo.setVisibleRowCount(10);

        ObservableList<User> userList = UserDAO.getUserList();
        userCombo.setItems(userList);
        userCombo.setVisibleRowCount(10);
    }


    public void actionSaveButton(ActionEvent actionEvent) throws IOException {
        int appointmentId = Integer.parseInt(appointmentIDTextField.getText());
        String appointmentTitle = appointmentTitleTextField.getText();
        String appointmentDescription = appointmentDescriptionTextField.getText();
        int appointmentContact = contactCombo.getValue().getContactId();
        String appointmentType = appointmentTypeTextField.getText();
        LocalDateTime appointmentStart = null;
        LocalDateTime appointmentEnd = null;
        appointmentStart = appointmentStart.of(startDatePicker.getValue(), startTimeCombo.getValue());
        appointmentEnd = appointmentEnd.of(endDatePicker.getValue(), endTimeCombo.getValue());
        int appointmentCustomerId = customerCombo.getValue().getCustomerId();
        int appointmentUserId = userCombo.getValue().getUserID();
        String appointmentLocation = appointmentLocationTextField.getText();
        String lastUpdatedBy = "script";
        Timestamp lastUpdated = Timestamp.valueOf(now());
        AppointmentDAO.updateAppointment(appointmentId, appointmentTitle, appointmentDescription, appointmentContact, appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentLocation);
        Appointment.backToAppointments(actionEvent);

    }

    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        Appointment.backToAppointments(actionEvent);
    }


    public void getAppointmentInfo(Appointment appointment) throws SQLException {
        appointmentIDTextField.setText(Integer.toString(appointment.getAppointmentId()));
        appointmentTitleTextField.setText(appointment.getAppointmentTitle());
        appointmentDescriptionTextField.setText(appointment.getAppointmentDescription());
        appointmentLocationTextField.setText(appointment.getAppointmentLocation());
        appointmentTypeTextField.setText(appointment.getAppointmentType());
        startDatePicker.setValue(appointment.getAppointmentStart().toLocalDate());
        startTimeCombo.setValue(appointment.getAppointmentStart().toLocalTime());
        endDatePicker.setValue(appointment.getAppointmentEnd().toLocalDate());
        endTimeCombo.setValue(appointment.getAppointmentEnd().toLocalTime());
        Contact d = ContactDAO.returnContactList(appointment.getAppointmentContact());
        contactCombo.setValue(d);
        Customer c = CustomerDAO.returnCustomerList(appointment.getAppointmentCustomerId());
        customerCombo.setValue(c);
        User u = UserDAO.returnUserId(appointment.getAppointmentUserId());
        userCombo.setValue(u);
    }
}
