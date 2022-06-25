package controller;

import DAO.AppointmentDAO;
import DAO.ContactDAO;
import DAO.CountryDAO;
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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Reports class
 *
 * @author Megan Riffey
 */
public class Reports implements Initializable {
    public TableView appointTypeTable;
    public TableView appointMonthTable;
    public TableView countryTable;
    @FXML
    private TableColumn<Appointment, String> appointTotalType;
    @FXML
    private TableColumn<Appointment, Integer> appointTypeTotal;
    @FXML
    private TableColumn<Appointment, String> appointMonth;
    @FXML
    private TableColumn<Appointment, Integer> appointMonthTotal;
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
    private TableView contactTable;

    // ObservableList that has all contacts from the database
    ObservableList<Contact> contactList = ContactDAO.getAllContacts();

    /**
     * Initializing Report tables and setting placeholders when no content is available in tables.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Contact Table, setting contact list combo, placeholder message and refreshing table between contacts
        contactCombo.setItems(contactList);
        contactCombo.setVisibleRowCount(10);
        contactTable.setPlaceholder(new Label("Please select a contact."));
        appointId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescription.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointContact.setCellValueFactory(new PropertyValueFactory<>("appointmentContactName"));
        appointStart.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustId.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointUserId.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));
        contactTable.refresh();

        // Appointments by Month and Totals for "Appointment Totals" tab
        appointMonthTable.setItems(AppointmentDAO.getAppointmentTypeMonth());
        appointMonthTable.setPlaceholder(new Label("No data for month is available."));
        appointMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointMonthTotal.setCellValueFactory(new PropertyValueFactory<>("appointmentTypeTotal"));

        // Appointment Type Table and Totals for "Appointment Totals" tab
        appointTypeTable.setPlaceholder(new Label("No data for Type is available."));
        appointTypeTable.setItems(AppointmentDAO.appointmentType());
        appointTotalType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointTypeTotal.setCellValueFactory(new PropertyValueFactory<>("appointmentTypeTotal"));

        // Contacts by Country
        countryTable.setItems(CountryDAO.countryTotals());
        countryTable.setPlaceholder(new Label("No data for Appointments by Country is available."));
        appointCountryTotal.setCellValueFactory(new PropertyValueFactory<>("countryMonthTotal"));
        appointCountry.setCellValueFactory(new PropertyValueFactory<>("countryMonth"));
    }

    /**
     * Method to populate contacts and to display associated appointments in table. Also displays if no appointments exist
     * for selected contact
     *
     * @param actionEvent event to populate associated appointments when contact is selected
     * @throws SQLException addresses unhandled SQL exception
     */
    public void contactPopulate(ActionEvent actionEvent) throws SQLException {
        // Getting contact name and converting it to contact ID to obtain associated appointments
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

    /**
     * Action event to take user back to the main menu when button is pressed
     *
     * @param actionEvent event for backToMenu button
     * @throws IOException addresses unhandled exception for load
     */
    public void backToMenu(ActionEvent actionEvent) throws IOException {
        new FXMLLoader();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
