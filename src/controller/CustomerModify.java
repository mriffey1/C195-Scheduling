package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FirstLvlDivisionDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLVLDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static java.time.LocalDateTime.now;

public class CustomerModify implements Initializable {
    public TextField customerIDTextField;
    public TextField customerNameTextField;
    public TextField customerPhoneTextField;
    public TextField customerAddressTextField;
    public TextField customerPostalTextField;
    public ComboBox<FirstLVLDivision> customerDivisionCombo;
    public ComboBox<Country> customerCountryCombo;
    public Button saveButton;
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<FirstLVLDivision> divisionList = FirstLvlDivisionDAO.getAllDivisionId();
        customerDivisionCombo.setItems(divisionList);
        customerDivisionCombo.setVisibleRowCount(10);

        ObservableList<Country> countryList = CountryDAO.getAllCountry();
        customerCountryCombo.setItems(countryList);
        customerCountryCombo.setVisibleRowCount(10);
    }

    /**
     * Action event for the save button. This will attempt to update the database and then redirect back to the Customers
     * tableview to display newly modified customer.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void actionSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            int customerId = Integer.parseInt(customerIDTextField.getText());
            String customerName = customerNameTextField.getText();
            if (customerNameTextField.getText().isEmpty()){
                helper.ErrorMsg.getError(1);
                return;
            }

            String customerAddress = customerAddressTextField.getText();
            if (customerAddressTextField.getText().isEmpty() || customerAddressTextField.getText().isBlank()) {
                helper.ErrorMsg.getError(1);
                return;
            }

            String customerPostalCode = customerPostalTextField.getText();
            if (customerPostalTextField.getText().isEmpty() || customerPostalTextField.getText().isBlank()) {
                helper.ErrorMsg.getError(1);
                return;
            }

            String customerPhone = customerPhoneTextField.getText();
            if (customerPhoneTextField.getText().isEmpty() || customerPhoneTextField.getText().isBlank()) {
                helper.ErrorMsg.getError(1);
                return;
            }

            int customerDivisionId = customerDivisionCombo.getValue().getDivisionID();
            int countryId = customerCountryCombo.getValue().getCountryId();
            String lastUpdatedBy = "script";
            Timestamp lastUpdated = Timestamp.valueOf(now());
            CustomerDAO.updateCustomer(customerName, customerAddress, customerPostalCode, customerPhone, lastUpdatedBy, lastUpdated, customerDivisionId, countryId, customerId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Action event for the cancel button. Upon click, it will redirect the user back to the main Customers tableview.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method gets the selected customer data from the database and displays it in the appropriate fields.
     *
     * @param customer
     * @throws SQLException
     */
    public void getCustomerInfo(Customer customer) throws SQLException {
        customerIDTextField.setText(Integer.toString(customer.getCustomerId()));
        customerNameTextField.setText(customer.getCustomerName());
        customerAddressTextField.setText(customer.getCustomerAddress());
        customerPhoneTextField.setText(customer.getCustomerPhone());
        customerPostalTextField.setText(customer.getCustomerPostalCode());
        FirstLVLDivision s = FirstLvlDivisionDAO.returnDivisionLevel(customer.getCustomerDivisionId());
        customerDivisionCombo.setValue(s);
        Country c = CountryDAO.returnCountry(customer.getCustomerCountryId());
        customerCountryCombo.setValue(c);

    }
}