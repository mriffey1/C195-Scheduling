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

public class AppointmentsAdd implements Initializable {
    @FXML
    private TextField appointmentIDTextField;
    @FXML
    private TextField appointTitleTextField;
    @FXML
    private TextField appointDescriptionTextField;
    @FXML
    private TextField appointLocationTextField;
    @FXML
    private TextField appointTypeTextField;
    @FXML
    private ComboBox<Contact> contactComboAdd;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker startDatePickerAdd;
    @FXML
    private ComboBox<LocalTime> startTimeComboAdd;
    @FXML
    private DatePicker endDatePickerAdd;
    @FXML
    private ComboBox<LocalTime> endTimeComboAdd;
    @FXML
    private ComboBox<User> userComboAdd;
    @FXML
    private ComboBox<Customer> customerComboAdd;
    private final int noOfDaysToAdd = 0;

    /**
     * Action event for save button that will verify and display the appropriate message for missing/blank fields,
     * along with verifying no overlapping appointments with respective customers and ensure appointments are scheduled
     * during business hours.
     *
     * @param actionEvent event for save button
     * @throws IOException addresses unhandled exceptions
     */
    public void actionSaveButton(ActionEvent actionEvent) throws IOException, SQLException {

        String appointmentTitle = appointTitleTextField.getText();
        String appointmentDescription = appointDescriptionTextField.getText();
        // LocalDateTime appointmentStart = null;
        //  LocalDateTime appointmentEnd = null;

        // Handling null pointer exception and alert message
        Contact contact = contactComboAdd.getValue();
        if (contact == null) {
            System.out.println("Please select the contact");
            return;
        }
        int appointmentContact = contact.getContactId();

        String appointmentType = appointTypeTextField.getText();
        // Handling null pointer exception and alert message
        LocalDate startPicker = startDatePickerAdd.getValue();
        if (startPicker == null) {
            System.out.println("Please select start date");
            return;
        }
        // Handling null pointer exception and alert message
        LocalTime start = startTimeComboAdd.getValue();
        if (start == null) {
            System.out.println("Please select time");
            return;
        }
        LocalDateTime appointmentStart = LocalDateTime.of(startDatePickerAdd.getValue(), startTimeComboAdd.getValue());
        // Handling null pointer exception and alert message
        LocalDate endPicker = endDatePickerAdd.getValue();
        if (endPicker == null) {
            System.out.println("Please select end date");
            return;
        }
        // Handling null pointer exception and alert message
        LocalTime end = endTimeComboAdd.getValue();
        if (end == null) {
            System.out.println("Please select end time");
            return;
        }
        LocalDateTime appointmentEnd = LocalDateTime.of(endDatePickerAdd.getValue(), endTimeComboAdd.getValue());
        // Handling null pointer exception and alert message
        Customer customer = customerComboAdd.getValue();
        if (customer == null) {
            System.out.println("Please select the customer");
            return;
        }
        int appointmentCustomerId = customerComboAdd.getValue().getCustomerId();
        // Handling null pointer exception and alert message
        User user = userComboAdd.getValue();
        if (user == null) {
            System.out.println("Please select a user");
            return;
        }
        int appointmentUserId = userComboAdd.getValue().getUserID();
        String appointmentLocation = appointLocationTextField.getText();

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
            AppointmentDAO.addAppointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContact);
            Appointment.backToAppointments(actionEvent);
        }
    }


    /**
     * Action event for cancel button that redirects the user back to the main appointments screen
     *
     * @param actionEvent event for cancel button
     * @throws IOException addresses unhandled exception
     */
    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        Appointment.backToAppointments(actionEvent);
    }

    /**
     * Method to add minutes onto End time combo based on start time combo.
     *
     * @param MinutesToAdd returns the number of minutes to add
     */
    public long plusMinutes(long MinutesToAdd) {
        try {
            return MinutesToAdd;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * LAMBDA EXPRESSION: Data Picker: takes value from start date and sets the value in the end date picker.
     * LAMBDA EXPRESSION: Time Combo: takes the time selected in the start combo and sets the value in the end time for 30 minutes later
     * Initializes the combo box fields and sets the end date picker to match the start date picker. Also adds
     * 30 minutes automatically onto the end time based on the selected start time.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIDTextField.setId(appointmentIDTextField.getId());
        startTimeComboAdd.setItems(Appointment.getTimes());
        endTimeComboAdd.setItems(Appointment.getTimes());
        ObservableList<Contact> contactList = ContactDAO.getAllContacts();
        contactComboAdd.setItems(contactList);
        ObservableList<User> userList = UserDAO.getUserList();
        userComboAdd.setItems(userList);
        ObservableList<Customer> customerList = CustomerDAO.getCustomerList();
        customerComboAdd.setItems(customerList);

        // Lambda expressions
        startDatePickerAdd.valueProperty().addListener((ov, oldValueDate, newValueDate) -> endDatePickerAdd.setValue(newValueDate.plusDays(noOfDaysToAdd)));
        startTimeComboAdd.valueProperty().addListener((observableValue, oldValueTime, newValueTime) -> endTimeComboAdd.setValue(newValueTime.plusMinutes(30)));
    }
}
