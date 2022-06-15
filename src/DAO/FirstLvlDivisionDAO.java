package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLVLDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLvlDivisionDAO {
    public static ObservableList<FirstLVLDivision> getAllDivisionId() {
        ObservableList<FirstLVLDivision> divisionList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
            PreparedStatement division = JDBC.conn.prepareStatement(sql);
            ResultSet rs = division.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                FirstLVLDivision d = new FirstLVLDivision(divisionId, divisionName, countryId);
                divisionList.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return divisionList;
    }

    public static FirstLVLDivision returnDivisionLevel(int divisionId) {
        try {
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.setInt(1, divisionId);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            int searchedDivisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            FirstLVLDivision s = new FirstLVLDivision(searchedDivisionId, divisionName);
            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<FirstLVLDivision> displayDivision(int countryId) throws SQLException {
        ObservableList<FirstLVLDivision> divisionCountryOptions = FXCollections.observableArrayList();

            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryId;
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                countryId = rs.getInt("Country_ID");

                FirstLVLDivision c = new FirstLVLDivision(divisionId, divisionName, countryId);
                divisionCountryOptions.add(c);
            }
            return divisionCountryOptions;
        }
    }


