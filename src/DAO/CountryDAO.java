package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Country SQL queries to obtain country information from database
 *
 * @author Megan Riffey
 */
public class CountryDAO {

    /**
     * SQL Query to get all countries and add to an observablelist
     *
     * @return countryList
     */
    public static ObservableList<Country> getAllCountry() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement country = JDBC.conn.prepareStatement(sql);
            ResultSet rs = country.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryId, countryName);
                countryList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countryList;
    }

    /**
     * SQL Query that returns country name based on country ID
     *
     * @param countryId country ID
     * @return c - countryId and countryName
     */
    public static Country returnCountry(int countryId) {
        try {
            String sql = "SELECT Country_ID, Country FROM countries WHERE Country_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, countryId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedCountryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country c = new Country(searchedCountryId, countryName);
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * ObservableList that displays the country totals and the number of customers per country for reports page
     *
     * @return customerCountry
     */
    public static ObservableList<Country> countryTotals() {
        ObservableList<Country> customerCountry = FXCollections.observableArrayList();
        try {
            String sql = "SELECT countries.Country, COUNT(customers.Customer_ID) AS Count FROM countries INNER JOIN first_level_divisions ON  countries.Country_ID = first_level_divisions.Country_ID INNER JOIN customers ON customers.Division_ID = first_level_divisions.Division_ID group by countries.Country";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String countryMonth = rs.getString("Country");
                int countryMonthTotal = rs.getInt("Count");
                Country results = new Country(countryMonth, countryMonthTotal);
                customerCountry.add(results);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerCountry;
    }
}