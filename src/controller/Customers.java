package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Customers implements Initializable {

    public TableColumn custCountryCol;
    @FXML
    private TableView<Customer> custTable;
    @FXML
    private TableColumn<Customer, Integer> custIdCol;
    @FXML
    private TableColumn<Customer, String> custNameCol;
    @FXML
    private TableColumn<Customer, String> custAddressCol;
    @FXML
    private TableColumn<Customer, String> custPostalCol;
    @FXML
    private TableColumn<Customer, String> custPhoneCol;
    @FXML
    private TableColumn<Customer, Integer> custFirstCol;
    public Button custDeleteLabel;
    public Button custAddLabel;
    public Button custUpdateLabel;


    ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

    /**
     * Action event for delete button on customer screen. If no customer is selected, an error message will be generated
     * and if a valid customer is selected and has no attached appointments, a warning message to confirm removal will be generated
     * and once OK is selected - the customer will be deleted from the database and the list of customers refreshed in the tableview.
     *
     * @param actionEvent
     * @throws Exception
     */
    public void actionCustDelete(ActionEvent actionEvent) throws Exception {
        int count = 0;
        ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();
        int selectedCustomer = custTable.getSelectionModel().getSelectedItem().getCustomerId();

        if (selectedCustomer == 0) {
            helper.ErrorMsg.getError(7);
        } else {
            for (Appointment appointment : appointmentList) {
                int appointmentCustId = appointment.getAppointmentCustomerId();
                if (appointmentCustId == selectedCustomer) {
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("Appointment found");
                Alert associatedAppoint = new Alert(Alert.AlertType.WARNING);
                associatedAppoint.setTitle("Alert");
                associatedAppoint.setHeaderText("Alert: " + count + " associated appointment(s).");
                associatedAppoint.setContentText("There are " + count + " associated appointment(s) for the selected customer.\n\n" +
                        "Please select OK to delete the associated appointments and customer.\n\n" +
                        "Otherwise, please press cancel to return to the main screen.");
                associatedAppoint.getButtonTypes().clear();
                associatedAppoint.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                associatedAppoint.getDialogPane().setMinHeight(250);
                associatedAppoint.getDialogPane().setMinWidth(400);
                associatedAppoint.showAndWait();
                if (associatedAppoint.getResult() == ButtonType.OK) {
                    for (Appointment appointment : appointmentList) {
                        if (appointment.getAppointmentCustomerId() == selectedCustomer)
                            AppointmentDAO.deleteAppointment(appointment.getAppointmentId());
                    }
                    CustomerDAO.deleteCustomer(custTable.getSelectionModel().getSelectedItem().getCustomerId());
                    helper.ErrorMsg.confirmation(2);
                    CustomerList = CustomerDAO.getCustomerList();
                    custTable.setItems(CustomerList);
                    custTable.refresh();
                } else if (associatedAppoint.getResult() == ButtonType.CANCEL) {
                    associatedAppoint.close();
                }
            }
            if (count == 0) {
                Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
                confirmRemoval.setTitle("Alert");
                confirmRemoval.setContentText("Remove selected customer?\n" +
                        "Press OK to remove.\nCancel to return to screen.");
                confirmRemoval.getButtonTypes().clear();
                confirmRemoval.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                confirmRemoval.showAndWait();
                if (confirmRemoval.getResult() == ButtonType.OK) {
                    CustomerDAO.deleteCustomer(custTable.getSelectionModel().getSelectedItem().getCustomerId());
                    helper.ErrorMsg.confirmation(2);
                    CustomerList = CustomerDAO.getCustomerList();
                    custTable.setItems(CustomerList);
                    custTable.refresh();
                } else if (confirmRemoval.getResult() == ButtonType.CANCEL) {
                    confirmRemoval.close();
                }
            }
        }
    }


    /**
     * Action event for add button to add a customer. It will redirect to the CustomerAdd file.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void actionCustAdd(ActionEvent actionEvent) throws IOException {
        //  FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerAdd.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Action event for update button for customers and will redirect to the CustomerModify file.
     * If no customer is selected - an error message will be generated and displayed.
     *
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void actionCustUpdate(ActionEvent actionEvent) throws IOException, SQLException {
        if (custTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/CustomerModify.fxml"));
            loader.load();
            CustomerModify MCController = loader.getController();
            MCController.getCustomerInfo(custTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();
        } else {
            helper.ErrorMsg.getError(7);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custTable.setItems(CustomerDAO.getCustomerList());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custFirstCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}