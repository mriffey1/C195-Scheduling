package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {

    public static ObservableList<Country> getAllCountry() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT Country_ID, Country FROM countries";
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
}
