package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    public TableView appointTypeTable;
    public TableView appointMonthTable;
    @FXML
    private TableColumn<Appointment, String> appointTotalType;
    @FXML
    private TableColumn appointTypeTotal;
    @FXML
    private TableColumn appointMonth;
    @FXML
    private TableColumn appointMonthTotal;
    @FXML
    private Tab contactSchedule;
    @FXML
    private TableColumn<Appointment, Integer> appointId;
    @FXML
    private TableColumn<Appointment, String> appointTitle;
    @FXML
    private TableColumn<Appointment, String> appointDescription;
    @FXML
    private TableColumn<Appointment, Integer> appointContact;
    @FXML
    private TableColumn<Appointment, String> appointType;
    @FXML
    private TableColumn<Appointment, Timestamp> appointStart;
    @FXML
    private TableColumn<Appointment, Timestamp> appointEnd;
    @FXML
    private TableColumn<Appointment, Integer> appointCustId;
    @FXML
    private TableColumn<Appointment, Integer> appointUserId;
    @FXML
    private ComboBox<Contact> contactCombo;
    @FXML
    private TableColumn appointCountry;
    @FXML
    private TableColumn appointCountryTotal;
    @FXML
    private Button backToMenu;
    @FXML
    private Tab appointCountryTab;
    @FXML
    private Tab contactScheduleTab;
    @FXML
    private Tab appointTotalTab;
    @FXML
    private TableView contactTable;
    ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();
    ObservableList<Contact> contactList = ContactDAO.getAllContacts();

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
        appointMonthTable.setPlaceholder(new Label("No data for month is available."));
        contactCombo.setItems(contactList);
        contactCombo.setVisibleRowCount(10);
        appointId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointStart.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustId.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointUserId.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));
        contactTable.setPlaceholder(new Label("Please select a contact."));
        contactTable.refresh();
        appointTypeTable.setPlaceholder(new Label("No data for Type is available."));

        ObservableList<Appointment> appointmentListType = AppointmentDAO.appointmentType();
        appointTypeTable.setItems(AppointmentDAO.appointmentType());
        appointTotalType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointMonthTable.setItems(AppointmentDAO.appointmentType());
        appointMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentStart1"));


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

    public void appointmentTotal() {


    }
}
