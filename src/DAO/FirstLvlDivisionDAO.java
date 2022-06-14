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
            String sql = "SELECT Division_ID, Division FROM first_level_divisions";
            PreparedStatement division = JDBC.conn.prepareStatement(sql);
            ResultSet rs = division.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                FirstLVLDivision d = new FirstLVLDivision(divisionId, divisionName);
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

//    public static FirstLVLDivision returnCountryByDivision(int countryId) {
//        ObservableList<FirstLVLDivision> divisionCountryId = FXCollections.observableArrayList();
//        try {
//            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Country_ID = ?";
//            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
//            ps.setInt(1, countryId);
//            ps.execute();
//
//            ResultSet rs = ps.getResultSet();
//            while (rs.next()) {
//                int divisionId = rs.getInt("Division_ID");
//                String divisionName = rs.getString("Division");
//                FirstLVLDivision s = new FirstLVLDivision(divisionId, divisionName);
//                divisionCountryId.add(s);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return (FirstLVLDivision) divisionCountryId;
//    }
}

