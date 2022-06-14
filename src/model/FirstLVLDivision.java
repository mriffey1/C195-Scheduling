package model;

public class FirstLVLDivision {

    private int divisionID;
    private String divisionName;
    private int countryId;



    public FirstLVLDivision(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;


    }

    public FirstLVLDivision(int divisionID, String divisionName, int countryId) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryId = countryId;



    }


    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionId(int divisionId) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return (divisionName);
    }
}
