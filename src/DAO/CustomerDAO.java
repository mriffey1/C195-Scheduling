package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {


    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> clist = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Country_ID FROM customers INNER JOIN first_level_divisions ON customers.Division_ID=first_level_divisions.Division_ID";
            PreparedStatement ps = JDBC.database().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int customerDivision = rs.getInt("Division_ID");
                Customer c = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivision);

                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }

}