package controller;

import DAO.AppointmentDAO;
import DAO.CustomerDAO;
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
import model.Country;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Customers implements Initializable {

    public TableColumn<Country, Integer> custCountryCol;
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
    @FXML
    private Button custDeleteLabel;
    @FXML
    private Button custAddLabel;
    @FXML
    private Button custUpdateLabel;


    ObservableList<Customer> CustomerList = CustomerDAO.getCustomerList();

    /**
     * Action event for delete button on customer screen. If no customer is selected, an error message will be generated
     * and if a valid customer is selected and has no attached appointments, a warning message to confirm removal will be generated
     * and once OK is selected - the customer will be deleted from the database and the list of customers refreshed in the tableview.
     * If appointments are found for a customer - an alert will be generated to confirm both removal of the associated appointments and customer.
     *
     * @param actionEvent event for when delete button on customer screen is pressed
     */
    public void actionCustDelete(ActionEvent actionEvent) {
        int count = 0;
        ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();

        Customer customer = custTable.getSelectionModel().getSelectedItem();
        if (customer == null) {
            helper.ErrorMsg.getError(7);
            return;
        }
        int selectedCustomer = custTable.getSelectionModel().getSelectedItem().getCustomerId();


        for (Appointment appointment : appointmentList) {
            int appointmentCustId = appointment.getAppointmentCustomerId();
            if (appointmentCustId == selectedCustomer) {
                count++;
            }
        }
        if (count > 0) {
            Alert associatedAppoint = new Alert(Alert.AlertType.WARNING);
            associatedAppoint.setTitle("Alert");
            associatedAppoint.setHeaderText("Alert: " + count + " associated appointment(s).");
            associatedAppoint.setContentText("There is " + count + " associated appointment(s) for the selected customer.\n\n" +
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


    /**
     * Action event for add button to add a customer. It will redirect to the CustomerAdd file.
     *
     * @param actionEvent event for add button for customer
     * @throws IOException addresses unhandled exception for load
     */
    public void actionCustAdd(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/CustomerAdd.fxml")));
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
     * @param actionEvent event to load screen to modify an existing customer
     * @throws IOException  addresses unhandled exceptions
     * @throws SQLException addresses unhandled SQL exception
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

    /**
     * Initializes customer information on customer screen
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custTable.setItems(CustomerDAO.getCustomerList());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custFirstCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionName"));
        custCountryCol.setCellValueFactory(new PropertyValueFactory<>("customerCountryName"));
    }

    /**
     * Button that takes a user back to the main menu screen
     *
     * @param actionEvent action for backToMenu button
     * @throws IOException addresses unhandled exception
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