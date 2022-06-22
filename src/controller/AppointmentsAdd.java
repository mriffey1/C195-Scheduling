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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentsAdd implements Initializable {
    public TextField appointmentIDTextField;
    public TextField appointTitleTextField;
    public TextField appointDescriptionTextField;
    public TextField appointLocationTextField;
    public TextField appointTypeTextField;
    @FXML
    private ComboBox<Contact> contactComboAdd;
    public Button saveButton;
    public Button cancelButton;
    public DatePicker startDatePickerAdd;
    public ComboBox<LocalTime> startTimeComboAdd;
    public DatePicker endDatePickerAdd;
    public ComboBox<LocalTime> endTimeComboAdd;
    public ComboBox<User> userComboAdd;
    public ComboBox<Customer> customerComboAdd;
   // public static boolean requiredInputCheck;
    private final int noOfDaysToAdd = 0;

    public void actionSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            String appointmentTitle = appointTitleTextField.getText();
            String appointmentDescription = appointDescriptionTextField.getText();
            LocalDateTime appointmentStart = null;
            LocalDateTime appointmentEnd = null;


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
            appointmentStart = LocalDateTime.of(startDatePickerAdd.getValue(), startTimeComboAdd.getValue());
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
            appointmentEnd = LocalDateTime.of(endDatePickerAdd.getValue(), endTimeComboAdd.getValue());
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
            } else {
                AppointmentDAO.addAppointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContact);
                Appointment.backToAppointments(actionEvent);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        Appointment.backToAppointments(actionEvent);
    }

    public LocalTime plusMinutes(long MinutesToAdd) {
        return plusMinutes(MinutesToAdd);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTimeComboAdd.setItems(Appointment.getTimes());
        endTimeComboAdd.setItems(Appointment.getTimes());
        ObservableList<Contact> contactList = ContactDAO.getAllContacts();
        contactComboAdd.setItems(contactList);
        ObservableList<User> userList = UserDAO.getUserList();
        userComboAdd.setItems(userList);
        ObservableList<Customer> customerList = CustomerDAO.getCustomerList();
        customerComboAdd.setItems(customerList);
        startDatePickerAdd.valueProperty().addListener((ov, oldValue, newValue) -> {
            endDatePickerAdd.setValue(newValue.plusDays(noOfDaysToAdd));
        });
        startTimeComboAdd.valueProperty().addListener((observableValue, oldValueTime, newValueTime) -> {
            endTimeComboAdd.setValue(newValueTime.plusMinutes(30));
        });
    }
}
