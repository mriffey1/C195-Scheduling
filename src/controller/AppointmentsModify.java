package controller;

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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    public ComboBox startTimeCombo;
    public DatePicker endDatePicker;
    public ComboBox endTimeCombo;
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


    public void actionSaveButton(ActionEvent actionEvent) {
    }

    public void actionCancelButton(ActionEvent actionEvent) {
    }

    public void actionDatePicker(ActionEvent actionEvent) {
    }

    public void getAppointmentInfo(Appointment appointment) throws SQLException {
        appointmentIDTextField.setText(Integer.toString(appointment.getAppointmentId()));
        appointmentTitleTextField.setText(appointment.getAppointmentTitle());
        appointmentDescriptionTextField.setText(appointment.getAppointmentDescription());
        appointmentLocationTextField.setText(appointment.getAppointmentLocation());
        appointmentTypeTextField.setText(appointment.getAppointmentType());
        Contact d = ContactDAO.returnContactList(appointment.getAppointmentContact());
        contactCombo.setValue(d);
        Customer c = CustomerDAO.returnCustomerList(appointment.getAppointmentCustomerId());
        customerCombo.setValue(c);
        User u = UserDAO.returnUserId(appointment.getAppointmentUserId());
        userCombo.setValue(u);
    }
}
