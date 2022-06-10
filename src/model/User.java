package model;

/**
 * Model Class that creates the User Objects
 *
 * @author Megan Riffey
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    public User(int userID, String userName, String password, String createdBy, String lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the userID
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the userID
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Returns the userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the userName
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the createdBy
     *
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the createdBy
     *
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}