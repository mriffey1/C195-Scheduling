package model;

import java.time.LocalDateTime;

/**
 * Model for Customers
 *
 * @author Megan Riffey
 */
public class Customer {
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private int customerDivisionId;
    private int customerCountryId;
    private String customerDivisionName;
    private String customerCountryName;
    private int customerId;

    // static ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

    public Customer() {
    }


//    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone,
//                    String createdBy, String lastUpdatedBy, int customerDivisionId, String customerDivisionName, int customerCountryId) {
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.customerAddress = customerAddress;
//        this.customerPostalCode = customerPostalCode;
//        this.customerDivisionId = customerDivisionId;
//        this.customerDivisionName = customerDivisionName;
//        this.customerCountryId = customerCountryId;
//        this.customerPhone = customerPhone;
//        this.createdBy = createdBy;
//        this.lastUpdated = lastUpdated;
//        this.lastUpdatedBy = lastUpdatedBy;
//
//    }


    // private static ObservableList<Customer> customerList = CustomerDAO.getCustomerList();

    public Customer(String customerName, String customerAddress, String customerPostalCode, String customerPhone, String createdBy, String lastUpdatedBy, int customerDivisionId, String customerDivisionName, int customerCountryId, String customerCountryName, int customerId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivisionId = customerDivisionId;
        this.customerDivisionName = customerDivisionName;
        this.customerCountryId = customerCountryId;
        this.customerCountryName = customerCountryName;
        this.customerPhone = customerPhone;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }


//
//    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, String lastUpdatedBy, Timestamp lastUpdated, int customerDivisionId, int countryId) {
//        this.customerId = customerId;
//        this.customerName = customerName;
//        this.customerAddress = customerAddress;
//        this.customerPostalCode = customerPostalCode;
//        this.customerDivisionId = customerDivisionId;
//        this.customerDivisionName = customerDivisionName;
//        this.customerCountryId = customerCountryId;
//        this.customerPhone = customerPhone;
//       this.createdBy = createdBy;
//     //   this.lastUpdated = lastUpdated;
//        this.lastUpdatedBy = lastUpdatedBy;
//    }

    // public static void updateCustomer(int Index, Customer customer){
    //       customerList.set(Index, customer);


    //   }
    public String getCustomerDivisionName() {
        return customerDivisionName;
    }

    public String getCustomerCountryName() {
        return customerCountryName;
    }


    /**
     * Gets customerId
     *
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customerId
     *
     * @param customerId customer id
     */
    public void setCustomerID(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns customer's name
     *
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set's customer's name
     *
     * @param customerName customer's name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Return's customer's address
     *
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets customer address
     *
     * @param customerAddress customer's address
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Returns customer postal code
     *
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Sets customer postal code
     *
     * @param customerPostalCode customer's postal code
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Returns customerDivision Code
     *
     * @return customerDivisionId
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     * Sets customer's division code
     *
     * @param customerDivisionId associated division Id
     */
    public void setCustomerDivision(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * Getter for customer ID for customer
     *
     * @return customerCountryId
     */
    public int getCustomerCountryId() {
        return customerCountryId;
    }

    /**
     * Setter for customer country Id
     *
     * @param customerCountryId country Id associated with customer
     */
    public void setCustomerCountryId(int customerCountryId) {
        this.customerCountryId = customerCountryId;
    }

    /**
     * Constructor for customer Id and customer name
     *
     * @param customerId   customer's id number
     * @param customerName customer's name
     */
    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    /**
     * Returns customer's phone number
     *
     * @return customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Set's customer's phone number
     *
     * @param customerPhone customer's phone number
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Returns customerName as string
     *
     * @return customerName
     */
    @Override
    public String toString() {
        return "#" + Integer.toString(customerId) + " - " + customerName;
    }
}
