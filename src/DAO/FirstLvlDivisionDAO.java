package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLVLDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FirstLvlDivisionDAO {
//    public static ObservableList<FirstLVLDivision> getAllDivisionId() {
//        ObservableList<FirstLVLDivision> divisionList = FXCollections.observableArrayList();
//        try {
//            String sql = "SELECT  * FROM first_level_divisions";
//            PreparedStatement division = JDBC.conn.prepareStatement(sql);
//            ResultSet rs = division.executeQuery();
//
//            while (rs.next()) {
//                int divisionId = rs.getInt("Division_ID");
//                String divisionName = rs.getString("Division");
//                int countryId = rs.getInt("Country_ID");
//                Timestamp Create_Date = rs.getTimestamp("Create_Date");
//                LocalDateTime createDate = Create_Date.toLocalDateTime();
//                String createBy = rs.getString("Created_By");
//                Timestamp Last_Updated = rs.getTimestamp("Last_Update");
//                LocalDateTime lastUpdated = Last_Updated.toLocalDateTime();
//                String lastUpdatedBy = rs.getString("Last_Updated_By");
//                FirstLVLDivision d = new FirstLVLDivision(divisionId, divisionName, countryId, createDate, createBy, lastUpdated, lastUpdatedBy);
//                divisionList.add(d);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return divisionList;
//    }

    /**
     * SQL query that returns division name from division ID
     * @param divisionId division ID
     * @return c - division Id and Division Name
     */
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

    /**
     * SQL Query that selects associated division ID and division name from associated countryId for combo boxes
     * @param countryId country ID
     * @return divisionCountryOptions
     * @throws SQLException addresses unhandled SQL exception
     */
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
                Timestamp Create_Date = rs.getTimestamp("Create_Date");
                LocalDateTime createDate = Create_Date.toLocalDateTime();
                String createBy = rs.getString("Created_By");
                Timestamp Last_Updated = rs.getTimestamp("Last_Update");
                LocalDateTime lastUpdated = Last_Updated.toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                FirstLVLDivision c = new FirstLVLDivision(divisionId, divisionName, countryId, createDate, createBy, lastUpdated, lastUpdatedBy);
                divisionCountryOptions.add(c);
            }
            return divisionCountryOptions;
        }
    }