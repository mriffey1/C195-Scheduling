package controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FirstLvlDivisionDAO;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class CustomerAdd implements Initializable {
    public TextField customerIDTextField;
    public TextField customerNameTextField;
    public TextField customerPhoneTextField;
    public TextField customerAddressTextField;
    public TextField customerPostalTextField;
    public ComboBox<FirstLVLDivision> customerDivisionCombo;
    public ComboBox<Country> customerCountryCombo;

    /**
     * Action event for save button. This will add the entered customer data into the database and then
     * redirect upon successful addition back to the main Customers tableview.
     * @param actionEvent
     * @throws IOException
     */
    public void actionSaveButton(ActionEvent actionEvent) throws IOException {
        try {
            String customerName = customerNameTextField.getText();
            String customerAddress = customerAddressTextField.getText();
            String customerPostalCode = customerPostalTextField.getText();
            String customerPhone = customerPhoneTextField.getText();
            FirstLVLDivision divId = customerDivisionCombo.getValue();
            LocalDateTime createdDate = LocalDateTime.now();
            LocalDateTime lastUpdated = LocalDateTime.now();
            int divisionId = divId.getDivisionID();
            CustomerDAO.addCustomer(customerName, customerAddress, customerPostalCode, customerPhone, createdDate, lastUpdated,  divisionId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test");
        backToCustomers(actionEvent);
    }

    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        backToCustomers(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountryCombo.setItems(CountryDAO.getAllCountry());

    }

    public void actionCountryLoad(ActionEvent actionEvent) {
        Country C = customerCountryCombo.getValue();
        try {
            customerDivisionCombo.setItems(FirstLvlDivisionDAO.displayDivision(C.getCountryId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToCustomers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}