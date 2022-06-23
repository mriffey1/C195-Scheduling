package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerDAO {



    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Create_Date, customers.Last_Update, customers.Created_By, customers.Last_Updated_By, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY customers.Customer_ID ";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                String createdBy = rs.getString("Created_By");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerDivisionId = rs.getInt("Division_ID");
                String customerDivisionName = rs.getString("Division");
                int customerCountryId = rs.getInt("Country_ID");
                String customerCountryName = rs.getString("Country");
                Customer c = new Customer(customerName, customerAddress, customerPostalCode, customerPhone, createdBy, lastUpdatedBy, customerDivisionId, customerDivisionName, customerCountryId, customerCountryName, customerId);
                customerList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public static Customer customerIdDisplay(int customerId) {
        try {
            String sqlStatement = "SELECT * FROM customers WHERE Customer_ID  = '" + customerId + "'";
            PreparedStatement deleteCust = JDBC.conn.prepareStatement(sqlStatement);
            deleteCust.setInt(1, customerId);
            deleteCust.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public static void updateCustomer(int customerId, String customerName, String customerAddress, String customerPostalCode,
                                      String customerPhone, String lastUpdatedBy, Timestamp lastUpdated, int customerDivisionId, int countryId) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Updated_By = ?, Last_Update = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement updateCust = JDBC.conn.prepareStatement(sql);
            updateCust.setString(1, customerName);
            updateCust.setString(2, customerAddress);
            updateCust.setString(3, customerPostalCode);
            updateCust.setString(4, customerPhone);
            updateCust.setString(5, lastUpdatedBy);
            updateCust.setTimestamp(6, lastUpdated);
            updateCust.setInt(7, customerDivisionId);
            updateCust.setInt(8, customerId);
            updateCust.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, LocalDateTime createdDate, LocalDateTime lastUpdated, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertCust = JDBC.conn.prepareStatement(sql);
        insertCust.setString(1, customerName);
        insertCust.setString(2, customerAddress);
        insertCust.setString(3, customerPostalCode);
        insertCust.setString(4, customerPhone);
        insertCust.setTimestamp(5, Timestamp.valueOf(createdDate));
        insertCust.setTimestamp(6, Timestamp.valueOf(lastUpdated));
        insertCust.setInt(7, divisionId);
        insertCust.executeUpdate();
    }


    public static Customer returnCustomerList(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.execute();
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int searchedCustomerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");

            Customer c = new Customer(searchedCustomerId, customerName);
            return c;
        }
        return null;
    }
}