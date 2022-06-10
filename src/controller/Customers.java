package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Customers implements Initializable {

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

    public void actionCustDelete(ActionEvent actionEvent) throws Exception {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            System.out.println("Error");
        } else {
            CustomerDAO.deleteCustomer(custTable.getSelectionModel().getSelectedItem().getCustomerId());
            CustomerList = CustomerDAO.getCustomerList();
            custTable.setItems(CustomerList);
            custTable.refresh();
        }

    }

    public void actionCustAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerAdd.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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
            stage.show();
        } else {

            System.out.println("Error");
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
        custFirstCol.setCellValueFactory(new PropertyValueFactory<>("customerDivision"));


    }
}




