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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Appointments class
 *
 * @author Megan Riffey
 */
public class Appointments implements Initializable {

    @FXML
    private ToggleGroup appointmentView;
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


    ObservableList<Appointment> AppointmentList = FXCollections.observableArrayList();

    /**
     * Action event for delete button on appointment screen. If no appointment is selected - an error dialog will
     * inform the user.
     *
     * @param actionEvent event for delete button
     */
    public void actionAppointDelete(ActionEvent actionEvent) {
        Appointment selectedAppointment = appointTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            helper.ErrorMsg.getError(12);
        } else {
            Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
            confirmRemoval.setTitle("Alert");
            confirmRemoval.setContentText("Would you like to remove the selected appointment?");
            confirmRemoval.getButtonTypes().clear();
            confirmRemoval.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            confirmRemoval.showAndWait();
            if (confirmRemoval.getResult() == ButtonType.OK) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Alert");
                confirmation.setContentText("Appointment ID# " + appointTable.getSelectionModel().getSelectedItem().getAppointmentId() + " for " + appointTable.getSelectionModel().getSelectedItem().getAppointmentType() + " has been cancelled.");
                confirmation.getButtonTypes().clear();
                confirmation.getButtonTypes().addAll(ButtonType.OK);
                confirmation.showAndWait();

                AppointmentDAO.deleteAppointment(appointTable.getSelectionModel().getSelectedItem().getAppointmentId());
                AppointmentList = AppointmentDAO.getAppointmentList();
                appointTable.setItems(AppointmentList);
                appointTable.refresh();

            } else if (confirmRemoval.getResult() == ButtonType.CANCEL) {
                confirmRemoval.close();
            }
        }
    }

    /**
     * Action event for add button on appointment screen. When pressed - the user will be redirected to the add appointments
     * screen.
     *
     * @param actionEvent event for add button
     * @throws IOException addresses unhandled exception
     */
    public void actionAppointAdd(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/AppointmentsAdd.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Action event for update button on appointment screen. If no appointment is selected, the user will be presented
     * with an error message stating to select an appointment. Once an appointment is selected - the user will be
     * redirected to the modify appointments screen.
     *
     * @param actionEvent event for update button
     * @throws IOException  addresses unhandled exception
     * @throws SQLException addresses unhandled SQL exception
     */
    public void actionAppointUpdate(ActionEvent actionEvent) throws IOException, SQLException {
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
            helper.ErrorMsg.getError(12);
        }
    }

    /**
     * Initializes the 3 appointment table data into the associated table with navigation via tabs.
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointTable.setItems(AppointmentDAO.getAppointmentList());
        appointIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointContactCol.setCellValueFactory(new PropertyValueFactory<>("appointmentContactName"));
        appointTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointDateCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointEndDateCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointCustIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointUserIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));
    }

    /**
     * Action event for button to return the user back to the main menu
     *
     * @param actionEvent event for backToMenu button
     * @throws IOException addresses unhandled exceptions
     */
    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void allAppointments(ActionEvent actionEvent) {
        appointTable.setItems(AppointmentDAO.getAppointmentList());
    }

    public void monthlyAppointments(ActionEvent actionEvent) {
        appointTable.setItems(AppointmentDAO.getMonthlyAppointments());
        appointTable.setPlaceholder(new Label("Currently, no appointments exist within the next month."));
    }

    public void weeklyAppointments(ActionEvent actionEvent) {
        appointTable.setItems(AppointmentDAO.getWeeklyAppointments());
        appointTable.setPlaceholder(new Label("Currently, no appointments exist within the next week."));
    }
}
