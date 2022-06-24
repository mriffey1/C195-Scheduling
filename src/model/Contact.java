package model;

import DAO.ContactDAO;

public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * Overloaded constructor for Contact
     * @param contactId contact id
     * @param contactName contact name
     * @param contactEmail contact email
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;

    }

//    public Contact(int contactId, String contactName) {
//        this.contactId = contactId;
//        this.contactName = contactName;
//    }

    /**
     * Getter for contact id
     *
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }


    /**
     * Setter for contact id
     *
     * @param contactId contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Getter for contact name
     *
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Setter for contact name
     *
     * @param contactName contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Getter for contact email
     *
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Setter for contact email
     *
     * @param contactEmail contact email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Override to display contactName in place of contact Id
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return (contactName);
    }
}