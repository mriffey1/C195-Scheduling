package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contact SQL queries to obtain contact information from database
 *
 * @author Megan Riffey
 */
public class ContactDAO {
    /**
     * SQL Query to get all contacts and add to ObservableList
     *
     * @return contactList
     */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement contacts = JDBC.conn.prepareStatement(sql);
            ResultSet rs = contacts.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contact d = new Contact(contactId, contactName, contactEmail);
                contactList.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }

    /**
     * SQL Query to return contact information from contact Id
     *
     * @param contactId contact Id
     * @return s query
     */
    public static Contact returnContactList(int contactId) {
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, contactId);

            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedContactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            Contact s = new Contact(searchedContactId, contactName, contactEmail);
            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SQL Query to return contact ID based on contact name
     *
     * @param contactName contact name
     * @return contactId
     * @throws SQLException addresses unhandled SQL exception
     */
    public static int returnContactId(String contactName) throws SQLException {
        int contactId = 0;
        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.conn.prepareStatement(sql);
        ps.setString(1, contactName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            contactId = rs.getInt("Contact_ID");
        }
        return contactId;
    }
}
