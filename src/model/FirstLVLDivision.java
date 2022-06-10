package model;

public class FirstLVLDivision {

    private int divisionID;
    private String divisionName;

    public FirstLVLDivision(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
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
}
