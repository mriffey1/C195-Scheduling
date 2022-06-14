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
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Country_ID FROM customers INNER JOIN first_level_divisions ON customers.Division_ID=first_level_divisions.Division_ID";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int customerDivisionId = rs.getInt("Division_ID");
                int customerCountryId = rs.getInt("Country_ID");
                Customer c = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivisionId, customerCountryId);

                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static void deleteCustomer(int customerId) {
        try {
            String sqldelete = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement deleteCust = JDBC.conn.prepareStatement(sqldelete);
            deleteCust.setInt(1, customerId);
            deleteCust.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(String name, String address, String zip, String phone, int divisionId, int countryId, int id) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement updateCust = JDBC.conn.prepareStatement(sql);
            updateCust.setString(1, name);
            updateCust.setString(2, address);
            updateCust.setString(3, phone);
            updateCust.setString(4, zip);
            updateCust.setInt(5, divisionId);
           // updateCust.setInt(6, countryId);
            updateCust.setInt(6, id);
            updateCust.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID = ?, Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?)";
        PreparedStatement insertCust = JDBC.conn.prepareStatement(sql);
        insertCust.setString(1, customer.getCustomerName());
        insertCust.setString(2, customer.getCustomerAddress());
        insertCust.setString(3, customer.getCustomerPostalCode());
        insertCust.setString(4, customer.getCustomerPhone());
    }

//    public static ObservableList<Customer> getCustomersById() {
//        ObservableList<Customer> customerList = FXCollections.observableArrayList();
//        try {
//            String sql = "SELECT Customer_Name, Customer_ID FROM customers";
//
//            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                String customerName = rs.getString("Customer_Name");
//                int customerId = rs.getInt("Customer_ID");
//                Customer c = new Customer(customerName, customerId);
//                customerList.add(c);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customerList;
//    }


}