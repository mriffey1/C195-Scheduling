package model;

public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private int customerDivision;
    private String customerPhone;


    public Customer(){

    }
    public Customer(int customerID, String customerName, String customerAddress,
                    String customerPostalCode, int customerDivision, String customerPhone) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivision = customerDivision;
        this.customerPhone = customerPhone;
    }

    public Customer(Integer customerId, String customerName, String customerAddress, String customerPostal, String customerPhone, Integer customerDivision) {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }


    public int getCustomerDivision() {
        return customerDivision;
    }

    public void setCustomerDivision(int customerDivision) {
        this.customerDivision = customerDivision;
    }

    public Customer(String customerName, int customerID) {
        this.customerName = customerName;
        this.customerID = customerID;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
