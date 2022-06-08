package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    private static String query;
    private static Statement stmt;
    private static ResultSet result;
    public static ObservableList<Customer> getCustomerList()  throws SQLException, Exception {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();


            String sql = "SELECT * FROM Customers";
            makeQuery(sql);
            PreparedStatement ps = JDBC.database().prepareStatement(sql);
            System.out.println(sql);
            ResultSet result=getResult();

            while (result.next()) {
                System.out.println("ID: " + result.getInt("Customer_ID"));
                Integer customerID = result.getInt("Customer_ID");
                String customerName = result.getString("Customer_Name");
                String customerAddress = result.getString("Address");
                String customerPostalCode = result.getString("Postal_Code");
                String customerPhone = result.getString("Phone");
                Integer customerDivision = result.getInt("Division_ID");

                Customer customerResult= new Customer(customerID, customerName, customerAddress, customerPostalCode, customerPhone, customerDivision);
                customerList.add(customerResult);
            }
            return customerList;
        }

    public static ObservableList<Customer> getCustomersById() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_Name, Customer_ID FROM customers";
            PreparedStatement ps = JDBC.database().prepareStatement(sql);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String customerName = results.getString("Customer_Name");
                int customerID = results.getInt("Customer_ID");
                Customer newCustomer = new Customer(customerName, customerID);
                customerList.add(newCustomer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public static void makeQuery(String q){
        String query = q;
        try{
            Statement stmt = JDBC.database().createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=stmt.executeQuery(q);
            if(query.toLowerCase().startsWith("delete")||query.toLowerCase().startsWith("insert")||query.toLowerCase().startsWith("update"))
                stmt.executeUpdate(q);
        } catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
    public static ResultSet getResult(){
        return result;
    }
}
