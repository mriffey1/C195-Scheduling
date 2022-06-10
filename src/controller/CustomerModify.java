package controller;

import DAO.CustomerDAO;
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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerModify implements Initializable {
    public TextField customerIDTextField;
    public TextField customerNameTextField;
    public TextField customerPhoneTextField;
    public TextField customerAddressTextField;
    public TextField customerPostalTextField;
    public ComboBox customerDivisionCombo;
    public ComboBox customerCountryCombo;
    public Button saveButton;
    public Button cancelButton;


    public void actionSaveButton(ActionEvent actionEvent) throws IOException {

     try {
         int id = Integer.parseInt(customerIDTextField.getText());
         String name = customerNameTextField.getText();
         String address = customerAddressTextField.getText();
         String zip = customerPostalTextField.getText();
         String phone = customerPhoneTextField.getText();

         CustomerDAO.updateCustomer(name, address, zip, phone, id);
     }  catch(NumberFormatException e){
         e.printStackTrace();
     }

        System.out.println("Test");
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    public void actionCancelButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Test");
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerAdd.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void getCustomerInfo(Customer customer) throws SQLException {
        customerIDTextField.setText(Integer.toString(customer.getCustomerId()));
        customerNameTextField.setText(customer.getCustomerName());
        customerAddressTextField.setText(customer.getCustomerAddress());
        customerPhoneTextField.setText(customer.getCustomerPhone());
        customerPostalTextField.setText(customer.getCustomerPostalCode());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}