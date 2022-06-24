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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.FirstLVLDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;


public class CustomerAdd implements Initializable {
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

    /**
     * Action event for save button. This will add the entered customer data into the database and then
     * redirect upon successful addition back to the main Customers tableview.
     *
     * @param actionEvent event when save button is pressed
     * @throws IOException addresses unhandled exception
     */
    public void actionSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            if (customerNameTextField.getText().isEmpty() || customerNameTextField.getText().isBlank()) {
                helper.ErrorMsg.getError(14);
            } else if (customerAddressTextField.getText().isEmpty() || customerAddressTextField.getText().isBlank()) {
                helper.ErrorMsg.getError(15);
            } else if (customerPostalTextField.getText().isBlank() || customerPostalTextField.getText().isEmpty()) {
                helper.ErrorMsg.getError(16);
            } else if (customerDivisionCombo.getValue() == null) {
                Country country = customerCountryCombo.getValue();
                if (country == null) {
                    helper.ErrorMsg.getError(17);
                } else {
                    String customerName = customerNameTextField.getText();
                    String customerAddress = customerAddressTextField.getText();
                    String customerPostalCode = customerPostalTextField.getText();
                    String customerPhone = customerPhoneTextField.getText();
                    FirstLVLDivision divId = customerDivisionCombo.getValue();
                    LocalDateTime createdDate = LocalDateTime.now();
                    LocalDateTime lastUpdated = LocalDateTime.now();
                    int divisionId = divId.getDivisionID();
                    CustomerDAO.addCustomer(customerName, customerAddress, customerPostalCode, customerPhone, createdDate, lastUpdated, divisionId);
                    helper.ErrorMsg.confirmation(3);
                    backToCustomers(actionEvent);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Action event for cancel button. When button is pressed, will redirect back to main Customers screen.
     *
     * @param actionEvent event for cancel button
     * @throws IOException addresses unhandled exception
     */
    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        backToCustomers(actionEvent);
    }

    /**
     * Initializes the country combo with all countries
     *
     * @param url            URL
     * @param resourceBundle Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountryCombo.setItems(CountryDAO.getAllCountry());
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
     * Method to redirect back to main Customers screen
     *
     * @param actionEvent event for redirecting back to main Customers screen
     * @throws IOException addresses unhandled exceptions
     */
    public void backToCustomers(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Customers.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}