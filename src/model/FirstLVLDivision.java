package model;

import java.time.LocalDateTime;

/**
 * Model class for First Level Divisions
 *
 * @author Megan Riffey
 */
public class FirstLVLDivision {

    private int divisionID;
    private String divisionName;
    private int countryId;

    /**
     * Constructor for FirstLVLDivision
     *
     * @param divisionID   division ID
     * @param divisionName division name
     */
    public FirstLVLDivision(int divisionID, String divisionName) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * Constructor for FirstLVLDivision
     *
     * @param divisionID    division ID
     * @param divisionName  division name
     * @param countryId     country ID
     * @param createDate    created date
     * @param createdBy     created by
     * @param lastUpdated   last updated date
     * @param lastUpdatedBy last updated by
     */
    public FirstLVLDivision(int divisionID, String divisionName, int countryId, LocalDateTime createDate,
                            String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryId = countryId;


    }

    /**
     * Getter for division ID
     *
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter for division ID
     *
     * @param divisionId division ID
     */
    public void setDivisionId(int divisionId) {
        this.divisionID = divisionID;
    }

    /**
     * Getter for division name
     *
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Setter for division name
     *
     * @param divisionName division name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Getter for country ID associated to division ID
     *
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Setter for countryID
     *
     * @param countryId country ID
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Override to display divisionName as a string
     *
     * @return divisionName
     */
    @Override
    public String toString() {
        return (divisionName);
    }
}
