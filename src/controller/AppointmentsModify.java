package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    private TextField appointmentIDTextField;
    @FXML
    private TextField appointmentTitleTextField;
    @FXML
    private TextField appointmentDescriptionTextField;
    @FXML
    private TextField appointmentLocationTextField;
    @FXML
    private TextField appointmentTypeTextField;
    @FXML
    private ComboBox<Contact> contactCombo;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private ComboBox<LocalTime> startTimeCombo;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<LocalTime> endTimeCombo;
    @FXML
    private ComboBox<User> userCombo;
    @FXML
    private ComboBox<Customer> customerCombo;

    /**
     * Initializing combo boxes for contacts, customers and users and setting the values
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
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

    /**
     * Action for save button that will get values from each field and updating the appointment
     *
     * @param actionEvent event for save button
     * @throws IOException addresses unhandled exception
     */
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

    /**
     * Action event for cancel button that redirects the user back to the main appointments page
     *
     * @param actionEvent event for cancel button
     * @throws IOException addresses unhandled exceptions
     */
    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        Appointment.backToAppointments(actionEvent);
    }

    /**
     * Displays the appointment information in the appropriate field in the form
     *
     * @param appointment references appointment object
     * @throws SQLException addresses unhandled SQL exceptions
     */
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
