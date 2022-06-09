package model;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivision;



    public Customer(){

    }
    public Customer(int customerId, String customerName, String customerAddress,
                    String customerPostalCode, String customerPhone, int customerDivision) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerDivision = customerDivision;
        this.customerPhone = customerPhone;
    }

//    public Customer(Integer customerId, String customerName, String customerAddress, String customerPostal, String customerPhone, Integer customerDivision) {
//    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerID(int customerId) {
        this.customerId = customerId;
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

    public Customer(String customerName, int customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
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
