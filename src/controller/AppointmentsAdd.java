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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentsAdd implements Initializable {
    public TextField appointmentIDTextField;
    public TextField appointTitleTextField;
    public TextField appointDescriptionTextField;
    public TextField appointLocationTextField;
    public TextField appointTypeTextField;
    public ComboBox<Contact> contactComboAdd;
    public Button saveButton;
    public Button cancelButton;
    public DatePicker startDatePickerAdd;
    public ComboBox<LocalTime> startTimeComboAdd;
    public DatePicker endDatePickerAdd;
    public ComboBox<LocalTime> endTimeComboAdd;
    public ComboBox<User> userComboAdd;
    public ComboBox<Customer> customerComboAdd;

    private final int noOfDaysToAdd = 0;

    public void actionSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            String appointmentTitle = appointTitleTextField.getText();
            String appointmentDescription = appointDescriptionTextField.getText();
            LocalDateTime appointmentStart = null;
            LocalDateTime appointmentEnd = null;
            int appointmentContact = contactComboAdd.getValue().getContactId();
            String appointmentType = appointTypeTextField.getText();
            appointmentStart = appointmentStart.of(startDatePickerAdd.getValue(), startTimeComboAdd.getValue());
            appointmentEnd = appointmentEnd.of(endDatePickerAdd.getValue(), endTimeComboAdd.getValue());
            int appointmentCustomerId = customerComboAdd.getValue().getCustomerId();
            int appointmentUserId = userComboAdd.getValue().getUserID();
            String appointmentLocation = appointLocationTextField.getText();

            AppointmentDAO.addAppointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContact);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test");
        Appointment.backToAppointments(actionEvent);
    }


    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
    //    backToAppointments(actionEvent);
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
