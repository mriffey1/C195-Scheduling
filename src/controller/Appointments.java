package controller;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Appointments implements Initializable {

    @FXML
    private TableView<Appointment> appointTable11;
    @FXML
    private TableColumn<Appointment, Integer> appointIdCol11;
    @FXML
    private TableColumn<Appointment, String> appointTitleCol11;
    @FXML
    private TableColumn<Appointment, String> appointDescriptionCol11;
    @FXML
    private TableColumn<Appointment, String> appointLocationCol11;
    @FXML
    private TableColumn<Appointment, Integer> appointContactCol11;
    @FXML
    private TableColumn<Appointment, String> appointTypeCol11;
    @FXML
    private TableColumn<Appointment, Timestamp> appointDateCol11;
    @FXML
    private TableColumn<Appointment, Timestamp> appointEndDateCol11;
    @FXML
    private TableColumn<Appointment, Integer> appointCustIdCol11;
    @FXML
    private TableColumn<Appointment, Integer> appointUserIdCol11;
    @FXML
    private TableView<Appointment> appointTable;
    @FXML
    private TableColumn<Appointment, Integer> appointIdCol;
    @FXML
    private TableColumn<Appointment, String> appointTitleCol;
    @FXML
    private TableColumn<Appointment, String> appointDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> appointLocationCol;
    @FXML
    private TableColumn<Appointment, Integer> appointContactCol;
    @FXML
    private TableColumn<Appointment, String> appointTypeCol;
    @FXML
    private TableColumn<Appointment, Timestamp> appointDateCol;
    @FXML
    private TableColumn<Appointment, Timestamp> appointEndDateCol;
    @FXML
    private TableColumn<Appointment, Integer> appointCustIdCol;
    @FXML
    private TableColumn<Appointment, Integer> appointUserIdCol;
    public Button appointDeleteLabel;
    public Button appointAddLabel;
    public Button appointUpdateLabel;
    public Tab allAppointmentsTab;
    public Tab appointmentsThisWeek;
    public Tab appointmentsMonthly;
    public TabPane tabPane;
    @FXML
    private TableView<Appointment> appointTable1;
    @FXML
    private TableColumn<Appointment, Integer> appointIdCol1;
    @FXML
    private TableColumn<Appointment, String> appointTitleCol1;
    @FXML
    private TableColumn<Appointment, String> appointDescriptionCol1;
    @FXML
    private TableColumn<Appointment, String> appointLocationCol1;
    @FXML
    private TableColumn<Appointment, Integer> appointContactCol1;
    @FXML
    private TableColumn<Appointment, String> appointTypeCol1;
    @FXML
    private TableColumn<Appointment, Timestamp> appointDateCol1;
    @FXML
    private TableColumn<Appointment, Timestamp> appointEndDateCol1;
    @FXML
    private TableColumn<Appointment, Integer> appointCustIdCol1;
    @FXML
    private TableColumn<Appointment, Integer> appointUserIdCol1;

    ObservableList<Appointment> AppointmentList = FXCollections.observableArrayList();

    public void actionAppointDelete(ActionEvent actionEvent) {
        Appointment selectedAppointment = appointTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            System.out.println("Error");
        } else {
            Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
            confirmRemoval.setTitle("Alert");
            confirmRemoval.setContentText("Remove Selected Part?");
            confirmRemoval.getButtonTypes().clear();
            confirmRemoval.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            confirmRemoval.showAndWait();
            if (confirmRemoval.getResult() == ButtonType.OK) {
                AppointmentDAO.deleteAppointment(appointTable.getSelectionModel().getSelectedItem().getAppointmentId());
                helper.ErrorMsg.confirmation(2);
                AppointmentList = AppointmentDAO.getAppointmentList();
                appointTable.setItems(AppointmentList);
                appointTable.refresh();
            } else if (confirmRemoval.getResult() == ButtonType.CANCEL) {
                confirmRemoval.close();
            }
        }
    }

    public void actionAppointAdd(ActionEvent actionEvent) {
    }

    public void actionAppointUpdate(ActionEvent actionEvent) throws IOException {
        if (appointTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/AppointmentsModify.fxml"));
            loader.load();
            AppointmentsModify MCController = loader.getController();
            MCController.getAppointmentInfo(appointTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
       } else {

            System.out.println("Error");
       }
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

        appointTable11.setItems(AppointmentDAO.getMonthlyAppointments());
        appointIdCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitleCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescriptionCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointLocationCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointContactCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentContact"));
        appointTypeCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointDateCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEndDateCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustIdCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointUserIdCol11.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}