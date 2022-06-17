package model;

import java.time.LocalDateTime;

/**
 * Model for Customers
 *
 * @author Megan Riffey
 */
public class Customer {
    private int customerId;
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


    // static ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

    public Customer() {
    }



    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone,
                    String createdBy, String lastUpdatedBy, int customerDivisionId, int customerCountryId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivisionId = customerDivisionId;
        this.customerCountryId = customerCountryId;
        this.customerPhone = customerPhone;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;

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
     * @param customerId
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
     * @param customerName
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
     * @param customerAddress
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
     * @param customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Returns customerDivision Code
     *
     * @return
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     * Sets customer's division code
     *
     * @param customerDivisionId
     */
    public void setCustomerDivision(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    public int getCustomerCountryId() {
        return customerCountryId;
    }

    public void setCustomerCountryId(int customerCountryId) {
        this.customerCountryId = customerCountryId;
    }

    public Customer(int customerId, String customerName ) {
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
     * @param customerPhone
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
