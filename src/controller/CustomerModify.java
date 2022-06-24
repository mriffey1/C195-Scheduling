package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FirstLvlDivisionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.Objects;
import java.util.ResourceBundle;

import static java.time.LocalDateTime.now;

/**
 * CustomerModify class
 * @author Megan Riffey
 */
public class CustomerModify implements Initializable {
    @FXML
    private TextField customerIDTextField;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField customerPhoneTextField;
    @FXML
    private TextField customerAddressTextField;
    @FXML
    private TextField customerPostalTextField;
    @FXML
    private ComboBox<FirstLVLDivision> customerDivisionCombo;
    @FXML
    private ComboBox<Country> customerCountryCombo;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes list of countries in the country combo field
     *
     * @param url            URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountryCombo.setItems(CountryDAO.getAllCountry());
    }

    /**
     * Action event for the save button. This will attempt to update the database and then redirect back to the Customers
     * tableview to display newly modified customer. Appropriate error message will display if a field is blank. Upon selection of a country,
     * the division will update to reflect only options underneath the selected country.
     *
     * @param actionEvent event for save button when modifying a customer
     */
    public void actionSaveButton(ActionEvent actionEvent) {
        try {
            int customerId = Integer.parseInt(customerIDTextField.getText());
            String customerName = customerNameTextField.getText();
            if (customerNameTextField.getText().isEmpty()) {
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
            CustomerDAO.updateCustomer(customerId, customerName, customerAddress, customerPostalCode, customerPhone, lastUpdatedBy, lastUpdated, customerDivisionId, countryId);

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Customers.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Action event for the cancel button. Upon clicking the button, it will redirect the user back to the main Customers tableview.
     *
     * @param actionEvent event for cancel button
     * @throws IOException addresses unhandled exception for load
     */
    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Customers.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads associated/matching division information when country is selected
     *
     * @param actionEvent event to load division combo once country is selected
     */
    public void actionCountryLoad(ActionEvent actionEvent) {
        Country C = customerCountryCombo.getValue();

        try {
            customerDivisionCombo.setItems(FirstLvlDivisionDAO.displayDivision(C.getCountryId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method gets the selected customer data from the database and displays it in the appropriate fields.
     *
     * @param customer selects customer information
     * @throws SQLException addresses unhandled SQL exceptions
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
        Country C = customerCountryCombo.getValue();
        customerDivisionCombo.setItems(FirstLvlDivisionDAO.displayDivision(C.getCountryId()));
    }
}