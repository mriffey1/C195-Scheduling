package model;

import java.time.LocalDateTime;

/**
 * Model Class that creates the User Objects
 *
 * @author Megan Riffey
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * Overloaded Constructor for a user object
     *
     * @param userID        - the user's id
     * @param userName      user's username
     * @param password      user's password
     * @param createDate    user account created date
     * @param createdBy     user account created by
     * @param lastUpdate    user's last updated by date
     * @param lastUpdatedBy user's last updated by
     */
    public User(int userID, String userName, String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Overloaded constructor creating a user object for userName
     *
     * @param userName the user's username
     */
    public User(String userName) {
        this.userName = userName;
    }

    /**
     * Overloaded constructor creating a user object with userId and userName
     *
     * @param userId   user's user ID
     * @param userName user's username
     */
    public User(int userId, String userName) {
        this.userID = userId;
        this.userName = userName;
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

    /**
     * Getter that returns created date for user
     *
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Setter for create date for user
     *
     * @param createDate user's created date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for last updated date for user
     *
     * @return lastUpdate
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for last updated date for user
     *
     * @param lastUpdate user's last updated date
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Override to display userId/UserName as string
     *
     * @return userID/userName
     */
    @Override
    public String toString() {
        return "#" + Integer.toString(userID) + " - " + userName;
    }

}