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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

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
        String appointmentType = appointmentTypeTextField.getText();
        String appointmentLocation = appointmentLocationTextField.getText();

        // Handling null pointer exception and alert message
        Contact contact = contactCombo.getValue();
        if (contact == null){
            helper.ErrorMsg.getError(24);
        }
        int appointmentContact = contactCombo.getValue().getContactId();


        // Handling null pointer exception and alert message
        LocalDate startPicker = startDatePicker.getValue();
        if (startPicker == null) {
            helper.ErrorMsg.getError(18);
            return;
        }

        // Handling null pointer exception and alert message
        LocalTime startTime = startTimeCombo.getValue();
        if (startTime == null) {
            helper.ErrorMsg.getError(19);
            return;
        }
        LocalDateTime appointmentStart = LocalDateTime.of(startDatePicker.getValue(), startTimeCombo.getValue());

        // Handling null pointer exception and alert message
        LocalDate endPicker = endDatePicker.getValue();
        if (endPicker == null) {
            helper.ErrorMsg.getError(20);
            return;
        }

        // Handling null pointer exception and alert message
        LocalTime end = endTimeCombo.getValue();
        if (end == null) {
            helper.ErrorMsg.getError(21);
            return;
        }
        LocalDateTime appointmentEnd = LocalDateTime.of(endDatePicker.getValue(), endTimeCombo.getValue());

        // Handling null pointer exception and alert message
        Customer customer = customerCombo.getValue();
        if (customer == null) {
            helper.ErrorMsg.getError(22);
            return;
        }
        int appointmentCustomerId = customerCombo.getValue().getCustomerId();

        // Handling null pointer exception and alert message
        User user = userCombo.getValue();
        if (user == null) {
            helper.ErrorMsg.getError(23);
            return;
        }
        int appointmentUserId = userCombo.getValue().getUserID();

        // Checking and verifying text-fields are valid and not blank or empty
        if (appointmentTitle.isBlank() || appointmentTitle.isEmpty()) {
            helper.ErrorMsg.getError(8);
        } else if (appointmentDescription.isBlank() || appointmentDescription.isEmpty()) {
            helper.ErrorMsg.getError(9);
        } else if (appointmentType.isEmpty() || appointmentType.isBlank()) {
            helper.ErrorMsg.getError(10);
        } else if (appointmentLocation.isBlank() || appointmentLocation.isEmpty()) {
            helper.ErrorMsg.getError(11);
        } else if (Appointment.businessHours(appointmentStart, appointmentEnd)) {
            return;
        } else if (Appointment.overlapCheck(appointmentCustomerId, appointmentStart, appointmentEnd)) {
            return;
        } else {
            AppointmentDAO.updateAppointment(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContact);
            Appointment.backToAppointments(actionEvent);
            helper.ErrorMsg.confirmation(4);
        }
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
        startTimeCombo.setItems(Appointment.getTimes());
        endTimeCombo.setItems(Appointment.getTimes());
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
