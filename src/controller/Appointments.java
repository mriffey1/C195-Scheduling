package controller;

import DAO.AppointmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Appointments implements Initializable {
    public TableView appointTable;
    public TableColumn appointIdCol;
    public TableColumn appointTitleCol;
    public TableColumn appointDescriptionCol;
    public TableColumn appointLocationCol;
    public TableColumn appointContactCol;
    public TableColumn appointTypeCol;
    public TableColumn appointDateCol;
    public TableColumn appointEndDateCol;
    public TableColumn appointCustIdCol;
    public TableColumn appointUserIdCol;
    public Button appointDeleteLabel;
    public Button appointAddLabel;
    public Button appointUpdateLabel;
    public Tab allAppointmentsTab;
    public Tab appointmentsThisWeek;
    public Tab appointmentsMonthly;
    public TabPane tabPane;
    public TableView appointTable1;
    public TableColumn appointIdCol1;
    public TableColumn appointTitleCol1;
    public TableColumn appointDescriptionCol1;
    public TableColumn appointLocationCol1;
    public TableColumn appointContactCol1;
    public TableColumn appointTypeCol1;
    public TableColumn appointDateCol1;
    public TableColumn appointEndDateCol1;
    public TableColumn appointCustIdCol1;
    public TableColumn appointUserIdCol1;

    public void actionAppointDelete(ActionEvent actionEvent) {
    }

    public void actionAppointAdd(ActionEvent actionEvent) {
    }

    public void actionAppointUpdate(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointTable.setItems(AppointmentDAO.getAppointmentList());
        appointIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointContactCol.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointDateCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEndDateCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointUserIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));

        appointTable1.setItems(AppointmentDAO.getWeeklyAppointments());
        appointIdCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitleCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescriptionCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointLocationCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointContactCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointTypeCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointDateCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEndDateCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustIdCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointUserIdCol1.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));

    }
}