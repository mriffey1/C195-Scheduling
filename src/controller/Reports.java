package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    public TableColumn appointTotalType;
    public TableColumn appointTypeTotal;
    public TableColumn appointMonth;
    public TableColumn appointMonthTotal;
    public Tab contactSchedule;
    public TableColumn appointId;
    public TableColumn appointTitle;
    public TableColumn appointDescription;
    public TableColumn appointContact;
    public TableColumn appointType;
    public TableColumn appointStart;
    public TableColumn appointEnd;
    public TableColumn appointCustId;
    public TableColumn appointUserId;
    public ComboBox<Contact> contactCombo;
    public TableColumn appointCountry;
    public TableColumn appointCountryTotal;
    public Button backToMenu;
    public Tab appointCountryTab;
    public Tab contactScheduleTab;
    public Tab appointTotalTab;
    public TableView contactTable;

    ObservableList<Contact> contactList = ContactDAO.getAllContacts();
    ObservableList<Appointment> AppointmentList = AppointmentDAO.getAppointmentList();

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactCombo.setItems(contactList);
        contactCombo.setVisibleRowCount(10);

        appointId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        //  appointLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        //  appointTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointStart.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustId.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointUserId.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));
        contactTable.setPlaceholder(new Label("Please select a contact."));
        contactTable.refresh();
    }

    public void contactPopulate(ActionEvent actionEvent) throws SQLException {
        String contactName = String.valueOf(contactCombo.getValue());
        int contactId = ContactDAO.returnContactId(contactName);
        if (AppointmentDAO.getContactAppointment(contactId).isEmpty()) {
            contactTable.setPlaceholder(new Label(contactName + " has no appointments."));
            contactTable.refresh();
            for (int i = 0; i < contactTable.getItems().size(); i++) {
                contactTable.getItems().clear();
                contactTable.setPlaceholder(new Label(contactName + " has no appointments."));
            }
        } else {
            contactTable.setItems(AppointmentDAO.getContactAppointment(contactId));
        }
    }
}