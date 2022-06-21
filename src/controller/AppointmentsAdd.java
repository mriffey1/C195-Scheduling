package controller;

import DAO.ContactDAO;
import DAO.CustomerDAO;
import DAO.UserDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsAdd implements Initializable {
    public TextField appointmentIDTextField;
    public TextField appointTitleTextField;
    public TextField appointDescriptionTextField;
    public TextField appointLocationTextField;
    public TextField appointTypeTextField;
    public ComboBox contactComboAdd;
    public Button saveButton;
    public Button cancelButton;
    public DatePicker startDatePickerAdd;
    public ComboBox startTimeComboAdd;
    public DatePicker endDatePickerAdd;
    public ComboBox endTimeComboAdd;
    public ComboBox userComboAdd;
    public ComboBox customerComboAdd;

    private final int noOfDaysToAdd = 0;

    public void addContacts() {
        ObservableList<Contact> contactList = ContactDAO.getAllContacts();
        contactComboAdd.setItems(contactList);
    }

    public void userId() {
        ObservableList<User> userList = UserDAO.getUserList();
        userComboAdd.setItems(userList);
    }

    public void customerId() {
        ObservableList<Customer> customerList = CustomerDAO.getCustomerList();
        customerComboAdd.setItems(customerList);
    }

    public void actionSaveButton(ActionEvent actionEvent) {
    }

    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void updatingEndDate() {
        startDatePickerAdd.valueProperty().addListener((ov, oldValue, newValue) -> {
            endDatePickerAdd.setValue(newValue.plusDays(noOfDaysToAdd));
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addContacts();
        userId();
        customerId();
        updatingEndDate();
    }
}
